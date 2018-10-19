#!/bin/bash -e

# (C) 2018 GENIVI Alliance
# LICENSE: MPLv2

# ---- General settings ----

# Fixed cache location on all agents that support this.
AGENT_STANDARD_DL_DIR="/var/cache/yocto/downloads"
AGENT_STANDARD_SSTATE_DIR="/var/cache/yocto/sstate"
AGENT_STANDARD_SGX_LOCATION="/var/go/sgx_bin"
AGENT_STANDARD_SGX_GEN3_LOCATION="/var/go/rcar-gen3/gfx-mmp_ybsp-390_20180627"


# ---- Helper functions ----

fail=false

# deref() is some trickery, but useful when a variable *name* itself is created
# on-the-fly by the program, and then a value is looked for in that variable
# name.

# Return the value of a variable whose name is stored in another variable
deref() { eval echo \$$1 ; }

# For shell/environment variables, whether they are named explicitly in the
# program, or the name is generated on the fly, this single function
# encapsulate a check that there is a value defined for the variable.
ensure_var_is_defined() {
  var_name=$1
  current_value=$(deref $var_name)
  if [[ -z "$current_value" ]]; then
    echo "Please define environment var_name \$$var_name (found empty value)"
    fail=true  # We want all the errors reported, so don't halt yet.
  fi
}

# This function takes a variable name and a "default" value If no value was
# assigned to the variable already (e.g. from environment) the given "default"
# value is assigned.
define_with_default() {
  var_name=$1
  default="$2"
  current_value=$(deref $var_name)
  if [[ -z "$current_value" ]]; then
    eval $var_name="$default"
  fi
}

# Helper function to terminate the program on failure.
# This allows us to delay program termination a bit by setting fail flag
# precisely when the error is found, but a bit later on call the
# stop_if_failure function
stop_if_failure() {
  if [[ "$fail" == "true" ]] ; then
    exit 1
  fi
}

# As the function says... (inform user and exit)
stop_immediately() {
  echo "Fatal error occurred - stopping ci-build script"
  exit 2
}

# Append a given value to the project's bitbake local.conf file.
#
# $1 = A unique text-match that will be used to find if there
# are any similar lines already and then it will not be added again.  That
# pattern could be the exact text but for simplicity you'd often just put a
# word there.
# $2 = The exact text (make sure to quote it) to be appended to local.conf
append_local_conf() {
  LOCAL_CONF="$BASEDIR/build/conf/local.conf"
  if [[ -f "$LOCAL_CONF" ]]; then
    if fgrep -q "$1" "$LOCAL_CONF" ; then
      echo "Found variable ($1) in local conf - skipping append"
    else
      echo -n "Appending to local.conf: "
    cat <<EOT | tee -a "$LOCAL_CONF"
$2
EOT
    fi
  else
    echo "Fatal: Did not find local.conf where expected"
    stop_immediately
  fi
}

# Same principle as described above but for bblayers
append_bblayers_conf() {
  BBLAYERS_CONF="$BASEDIR/build/conf/bblayers.conf"
  if [[ -f "$BBLAYERS_CONF" ]]; then
    if fgrep -q "$1" "$BBLAYERS_CONF" ; then
      echo "Found variable ($1) in bblayers.conf - skipping append"
    else
      echo -n "Appending to bblayers.conf: "
    cat <<EOT | tee -a "$BBLAYERS_CONF"
$2
EOT
    fi
  else
    echo "Fatal: Did not find bblayers.conf where expected"
    stop_immediately
  fi
}


# Copy or move data from the build tree to the staging directory
# The first argument is either "cp" or "mv".  The (multiple) argumentns
# that follow specify files/directories to copy or move.
# - The function will handle both files and directories
# - The list of paths might be created by a glob, such as *.log
#   If such a list turns out to be empty, the function handles it 
#   silently, where a straight cp, or mv would write out errors.
stage_artifact() {
  mkdir -p staging
  cmd=$1
  shift

  if [[ -z "$1" ]] ; then
    echo "Skipped an artifact pattern that matched nothing"
    return
  fi

  for f in $@ ; do  # <- could be empty glob list
    flag=
    if [[ -e "$f" ]] ; then
      if [[ $cmd == cp && -d "$f" ]] ; then
        flag=-a
      elif [[ $cmd == cp ]] ; then
        flag=--preserve=all
      fi
      $cmd $flag "$f" staging/
    else
      echo "Skipping non-existing (or already moved) artifact: $f"
    fi
  done
}

# Layer version overrides:

# If an override variable is defined, build with this version of the layer
# instead.  If multiple conflicting values, then the most specific override
# (commit) wins

# *NOTE*  Variables cannot have dashes in name therefore, underscores must be
# used!  For example: define LAYER_meta_openembedded for meta-openembedded layer

# To override a layer, define a variable like this in the environment
# before calling the script.

# LAYER_<layername>_FORK      <- specify a git URL where your variant is stored
# LAYER_<layername>_COMMIT    <- specify a commit hash
# LAYER_<layername>_BRANCH    <- specify a branch name
# LAYER_<layername>_TAG       <- specify a tag namae

# Note that a logical priority order is applied, as follows:
# - FORK is always applied - any specified commit/branch/tag will refer to the repo fetched from the FORK url)
# - TAG overrides BRANCH
# - COMMIT hash is more specific than both TAG and BRANCH and shall take priority if defined.
layer_override() {
  local name=$1 suffix var_name value d

  # Variables cannot have dashes in name therefore, underscores must be
  # used.  For example: LAYER_meta_browser_COMMIT for meta-browser layer
  clean_name=$(echo "$name" | sed 's/-/_/g')

  # First FORK...
  var_name="LAYER_${clean_name}_FORK"
  value=$(deref "$var_name")
  if [ -n "$value" ] ; then
    echo "***** NOTE: OVERRIDING LAYER $name USING \$${var_name} = $value *****"
    d="$PWD"
    cd $name || echo "Layer name wrong?  Can't cd to dir : $name"
    echo "Fetching from $value"
    git remote add temp_fork "$value"
    git fetch temp_fork

    # Master is assumed, but it is likely overridden by a COMMIT/TAG/BRANCH
    # value below. but this could fail.
    echo "NOTE: Assuming master branch for now (might be overridden later)"
    echo "NOTE: This will fail if no master branch exists in $value"
    echo "+ git reset temp_fork/master --hard"
    git reset temp_fork/master --hard
    cd "$d"
  fi

  # ...then the others
  for suffix in COMMIT TAG BRANCH ; do
    var_name="LAYER_${clean_name}_${suffix}"
    value=$(deref "$var_name")
    if [ -n "$value" ] ; then
      echo "***** NOTE: OVERRIDING LAYER $name USING \$${var_name} = $value *****"
      d="$PWD"
      cd $name || echo "Layer name wrong?  Can't cd to dir : $name"
      git fetch --all

      # If forked, make sure to use the right remote, otherwise origin
      # For branches, prefer the explicit: remote/branchname
      if git remote | grep -q temp_fork ; then
        git checkout "temp_fork/$value" 2>/dev/null || git checkout $value
      else
        git checkout "origin/$value" 2>/dev/null || git checkout $value
      fi

      cd "$d"
      break  # First one wins, priority order: COMMIT >= TAG >= BRANCH
    fi
  done
}

# Overrides are defined invisibly in environment variables so we should inform
# the user which ones are applied, for both interactive use and for studying
# a build log at a later time.
# NOTE:  Because of the order they are called we can't use output from the
# layer_override function above, so we have some repeated code here.
print_layer_overrides() {
  local names="$1" name clean_name suffix var_name value d

  for name in $names ; do
    clean_name=$(echo "$name" | sed 's/-/_/g')
    for suffix in COMMIT TAG BRANCH ; do
      var_name="LAYER_${clean_name}_${suffix}"
      value=$(deref "$var_name")
      if [ -n "$value" ] ; then
        echo "$var_name = $value"
      fi
    done
  done
}

# List all dependent layers here.  This list is used by the layer overrides
# functionality.
# NOTE If a super-project is applied, such as using submodules, then this list
# would be fetched from git instead, in case it changes.
LAYERS="
poky
meta-gplv2
meta-openembedded
renesas-rcar-gen3
"

# Clean up function is called at end of script, or as a signal handler (trap)
# if the script is interrupted
cleanup() {
  # Restore git config user - if this was not defined locally before then it is
  # unset (which might mean a global setting is used)
  if [ -z "$olduser" ] ; then
    git config --unset user.name
  else
    git config user.name "$olduser"
  fi
  if [ -z "$oldemail" ] ; then
    git config --unset user.email
  else
    git config user.email "$oldemail"
  fi
}

# Fetch URL to file name using either curl or wget, skipping if the file exists.
fetch() {
  echo "Downloading external file: $2"
  if [ ! -f "$1" ] ; then
    curl "$2" >"$1" || wget "$2" -O "$1"
  fi
}


# ---- Main program ----

trap cleanup SIGINT SIGTERM

D=$(dirname "$0")
cd "$D"
BASEDIR=$(readlink -f "$PWD/../..") # One dir above meta-ivi!

# The user must define this
ensure_var_is_defined TARGET

stop_if_failure

# The values can be overridden by defining environment variables
# If no value given, use this default:
define_with_default BUILD_SDK false
define_with_default BUILD_TEST_IMAGE false
define_with_default COPY_LICENSES false
define_with_default LAYER_ARCHIVE false
define_with_default CREATE_RELEASE_DIR false
define_with_default MIRROR "https://docs.projects.genivi.org/releases/yocto_mirror"
define_with_default PREMIRROR ""  # By default none (but we have the shared DL_DIR)
define_with_default RM_WORK false
define_with_default REUSE_STANDARD_DL_DIR false
define_with_default REUSE_STANDARD_SSTATE_DIR false
define_with_default SGX_DRIVERS $AGENT_STANDARD_SGX_LOCATION
define_with_default SGX_GEN_3_DRIVERS $AGENT_STANDARD_SGX_GEN3_LOCATION
define_with_default SOURCE_ARCHIVE false
define_with_default STANDARD_RELEASE_BUILD false

# This cleverly(?) reuses the r-car unique settings from the GDP project
# The purpose is to avoid maintaining two files, although to avoid accidental
# breakage we lock down the commit version here.
define_with_default GDP_TEMPLATES_URL 'https://raw.githubusercontent.com/GENIVI/genivi-dev-platform/05c711a68398bfcddf4339c666c3d07df7b197e8/gdp-src-build/conf/templates'

# The following only apply to temporary (local) dirs.  If any of
# REUSE_STANDARD_{DL,SSTATE}_DIR is defined then those directories will be
# used no matter what. Those reusable DL/SSTATE dirs are never cleared by
# this script.
define_with_default KEEP_DOWNLOADS false
define_with_default KEEP_SSTATE false

define_with_default KEEP_TMP false

stop_if_failure

#git_gdp="https://github.com/GENIVI/meta-ivi"
branch="master"

# cd workingdir
MACHINE="$TARGET" # For most boards - exceptions handled below

if [[ "$TARGET" == "r-car-m3-starter-kit" ]]; then
  MACHINE="m3ulcb"
fi

if [[ "$TARGET" == "r-car-h3-starter-kit" ]]; then
  MACHINE="h3ulcb"
fi

ensure_var_is_defined MACHINE
export MACHINE

# OVERRIDING VARIABLES
if [[ "$REUSE_STANDARD_DL_DIR" == "true" ]]; then
  DL_DIR="$AGENT_STANDARD_DL_DIR"
fi

if [[ "$REUSE_STANDARD_SSTATE_DIR" == "true" ]]; then
  SSTATE_DIR="$AGENT_STANDARD_SSTATE_DIR"
fi

if [[ "$STANDARD_RELEASE_BUILD" == "true" ]]; then
  SOURCE_ARCHIVE=true  # NOTE: overriding env settings
  COPY_LICENSES=true
  LAYER_ARCHIVE=true
  CREATE_RELEASE_DIR=true
fi

echo Configuration:
echo
echo "TARGET = $TARGET"
echo "FORK = $FORK"
echo "BRANCH = $BRANCH"
echo "TAG = $TAG"
echo "COMMIT = $COMMIT"
echo "RELEASE = $RELEASE (currently unused)"
print_layer_overrides "$LAYERS"
echo "BUILD_SDK = $BUILD_SDK"
echo "BUILD_TEST_IMAGE = $BUILD_TEST_IMAGE"
echo "COPY_LICENSES" = "$COPY_LICENSES"
echo "CREATE_RELEASE_DIR" = "$CREATE_RELEASE_DIR"
echo "DL_DIR = $DL_DIR"
echo "MIRROR" = "$MIRROR"
echo "PREMIRROR" = "$PREMIRROR"
echo "REUSE_STANDARD_DL_DIR = $REUSE_STANDARD_DL_DIR"
echo "REUSE_STANDARD_SSTATE_DIR = $REUSE_STANDARD_SSTATE_DIR"
echo "RM_WORK = $RM_WORK"
echo "SGX_DRIVERS = $SGX_DRIVERS"
echo "SGX_GEN_3_DRIVERS = $SGX_GEN_3_DRIVERS"
echo "SOURCE_ARCHIVE" = "$SOURCE_ARCHIVE"
echo "SSTATE_DIR = $DL_DIR"
echo "STANDARD_RELEASE_BUILD" = "$STANDARD_RELEASE_BUILD"

# INIT
cd "$BASEDIR/meta-ivi"
echo "*** Initializing layers"
scripts/checkout
echo "*** Initializing conf"
export TEMPLATECONF=$PWD/meta-ivi/conf
source ../poky/oe-init-build-env ../build

# build steps
# We are now in build/

# If DL/SSTATE are to be reused it is normally by the use of
# REUSE_STANDARD_DL_DIR and REUSE_STANDARD_SSTATE_DIR.  Local dirs left in the
# build directory are therefore normally wiped, unless these environment
# variables say otherwise.

# Additional environment variables that can be specified to modify
# caching behavior:
if [[ "$KEEP_DOWNLOADS" != "true" ]] ; then
  rm -rf downloads
fi

if [[ "$KEEP_SSTATE" != "true" ]]; then
   rm -rf cache sstate-cache
fi

if [[ "$KEEP_TMP" != "true" ]]; then
   rm -rf tmp
fi

cd "$BASEDIR/meta-ivi"

# Need to set an identity because if it is unset (which it could be in a CI
# environment), some patching commands will fail the build.
# Then, we must prefer to restore the user's git identity again, in case the
# script is used interactively.
set +e  # The following two commands can fail if value is unset
olduser="$(git config user.name)"
oldemail="$(git config user.email)"
set -e  # Back to strict failure checking - (abort script if any command fails)
git config user.name "CI build -- ignore"
git config user.email no_email

# Here follows overrides, not of individual layers, but of the main project
# itself.  Define the env. variables FORK, BRANCH, TAG, and/or COMMIT to
# fetch the parent project from another location or force a particular version
# to be built.  The comments about priority written above the layer_override()
# function apply also here.

# Normally the material (source code) is defined in the pipeline itself in the
# CIAT system but there are multiple ways to override it provided here.

if [[ -n "$FORK" ]]; then
  echo "***** NOTE: OVERRIDING REPOSITORY WITH \$FORK = $FORK *****"
  git remote set-url origin "$FORK"
  git fetch
  # V *danger* V.  One reason why you should not use this script if it's not in CI
  git reset origin/master --hard
  git checkout $BRANCH  # <- note this should be ok even if $BRANCH is an empty value
fi

if [[ -n "$BRANCH" ]]; then
  echo "***** NOTE: OVERRIDING CHOSEN COMMIT USING \$BRANCH = $BRANCH *****"
  git checkout $BRANCH
fi

if [[ -n "$TAG" ]]; then
  echo "***** NOTE: OVERRIDING CHOSEN COMMIT USING \$TAG = $TAG *****"
  git checkout $TAG
fi

if [[ -n "$COMMIT" ]]; then
  echo "***** NOTE: OVERRIDING CHOSEN COMMIT USING \$COMMIT = $COMMIT *****"
  git checkout $COMMIT
fi

# Do version override on sublayers (if any such overrides defined)
cd ..
for l in $LAYERS ; do
  layer_override $l
done

# LOCAL CONF MODIFICATIONS

# Also this is controlled by environment variables that can be set before
# calling the script.  Until there is external documentation, this code
# should be quite self-explanatory.

if [[ "$RM_WORK" == "true" ]]; then
  append_local_conf rm_work 'INHERIT += "rm_work"'
fi

if [[ -n "$DL_DIR" ]]; then
  append_local_conf DL_DIR "DL_DIR = \"$DL_DIR\""
fi

if [[ -n "$SSTATE_DIR" ]]; then
  append_local_conf SSTATE_DIR "SSTATE_DIR = \"$SSTATE_DIR\""
fi

if [[ -n "$BB_NUMBER_THREADS" ]]; then
  append_local_conf BB_NUMBER_THREADS "BB_NUMBER_THREADS = \"$BB_NUMBER_THREADS\""
fi

if [[ -n "$PARALLEL_MAKE" ]]; then
  echo $PARALLEL_MAKE | egrep -q '^-j' || PARALLEL_MAKE="-j$PARALLEL_MAKE"
  append_local_conf PARALLEL_MAKE "PARALLEL_MAKE = \"$PARALLEL_MAKE\""
fi

if [[ "$SOURCE_ARCHIVE" == "true" ]]; then
  append_local_conf ARCHIVER_MODE 'INHERIT += "archiver"'
  append_local_conf ARCHIVER_MODE 'ARCHIVER_MODE[src] = "original"'
fi

if [[ "$COPY_LICENSES" == "true" ]]; then
  append_local_conf COPY_LIC_DIRS 'COPY_LIC_DIRS = "1"'
  append_local_conf COPY_LIC_MANIFEST 'COPY_LIC_MANIFEST = "1"'
fi

# The own-mirrors bbclass is generally more convenient for PREMIRRORS, but
# this format makes it similar to the $MIRROR setup below, which needs to be
# explicit anyhow.

# We *pre*pend PREMIRROR because we want it to be the first mirror that is
# checked, if the user has defined any other in conf files.

if [[ -n "$PREMIRROR" ]]; then
  append_local_conf PREMIRRORS_prepend "
PREMIRRORS_prepend = \"\\
     git://.*/.* $PREMIRROR \\n \\
     ftp://.*/.* $PREMIRROR \\n \\
     http://.*/.* $PREMIRROR \\n \\
     https://.*/.* $PREMIRROR \\n \\
     \"
"
fi

# This is the "post" mirror (i.e. checked last).
# WE *app*pend MIRROR because we want it to be the last mirror that is checked,
# if the user has defined others in conf files.
if [[ -n "$MIRROR" ]]; then
  append_local_conf MIRRORS_append "
MIRRORS_append = \"\\
     git://.*/.* $MIRROR \\n \\
     ftp://.*/.* $MIRROR \\n \\
     http://.*/.* $MIRROR \\n \\
     https://.*/.* $MIRROR \\n \\
     \"
"
fi

# Deal with special setup, copy binary drivers etc.

# All R-Car targets start with "r-car-"
set +e
if echo "$TARGET" | egrep -q '^r-car-' ; then
  if [[ -d "$BASEDIR/meta-renesas" ]] ; then
    echo "Copying binary graphics and mmp drivers for $TARGET from: $SGX_GEN_3_DRIVERS"
    cd "$BASEDIR/meta-renesas"
    meta-rcar-gen3/docs/sample/copyscript/copy_evaproprietary_softwares.sh "$SGX_GEN_3_DRIVERS"
    append_bblayers_conf meta-renesas 'BBLAYERS += " \
      ${TOPDIR}/../meta-renesas/meta-rcar-gen3 \
      ${TOPDIR}/../meta-ivi-renesas \
      ${TOPDIR}/../meta-linaro/meta-optee \
      "'
  else
    echo "TARGET is set to $TARGET but I did not find a BSP layer (meta-renesas)"
    exit 2
  fi
fi
set -e

# Adjust local conf if building for Renesas SoC
# The purpose of this section is to reuse the special settings
# from GDP.  That way we don't need to maintain two copies of
# files.  This might be complicating things however... we're
# trying it for now.

case $TARGET in
  r-car-m3-starter-kit)
    target_local_conf_file=r-car-m3-starter-kit.local.conf
    ;;

  r-car-h3-starter-kit)
    target_local_conf_file=r-car-h3-starter-kit.local.conf
    ;;

  r-car-m3-salvator-x)
    target_local_conf_file=r-car-m3-salvator-x.local.conf
    ;;

  r-car-h3-salvator-x)
    target_local_conf_file=r-car-h3-salvator-x.local.conf
    ;;

  *)
    target_local_conf_file=
    ;;
esac


if [ -n "$target_local_conf_file" ] ; then
  # Download and use hardware-specific local conf settings (reused from the GDP project)
  cd "$BASEDIR/build/conf"
  local_conf_url="$GDP_TEMPLATES_URL/$target_local_conf_file"
  fetch "$target_local_conf_file" "$local_conf_url"

  # We need also the .inc file because it is recursively included
  # Put this one under /templates
  # We don't really use templates, just adjusting to what the GDP local conf file requires
  inc_file="renesas-rcar-gen3.local.inc"
  inc_url="$GDP_TEMPLATES_URL/$inc_file"
  mkdir -p templates
  cd templates
  fetch "$inc_file" "$inc_url"

  # And include the top level file at the bottom of local.conf
  append_local_conf .local.conf "require $target_local_conf_file"

  cd "$BASEDIR"
fi

# These environment variables control conditional compilation
# of test-image and a SDK-optimized image
if [[ "$BUILD_TEST_IMAGE" == "true" ]]; then
  append_bblayers_conf meta-ivi-test 'BBLAYERS += " ${TOPDIR}/../meta-ivi/meta-ivi-test"'
  bitbake test-image
elif [[ "$BUILD_SDK" == "true" ]]; then
  # (Anticipating a future sdk image:)
  bitbake pulsar-image-sdk
else
  # Normal image build
  bitbake pulsar-image
fi

cd "$BASEDIR"
rm -f logs.tar logs.tar.gz
find build/tmp/work \( -name "*.log" -o -name "log.*" -o -name "run.*" \) -print0 | xargs -0 tar uf logs.tar || true
gzip logs.tar || true

# The following will be copied/moved to the staging directory, but only if
# the files exist.  It will silently continue, with any files that do exist.
set +e  # Allow failures temporarily
rm -rf staging
shopt -s nullglob
stage_artifact mv build/tmp/deploy/licenses
stage_artifact mv build/tmp/deploy/licenses/genivi-dev-platform*/license.manifest
stage_artifact mv build/tmp/deploy/sdk*
stage_artifact cp build/tmp/deploy/images/*
stage_artifact mv build/tmp/deploy/sources
stage_artifact cp build/conf/*.conf
stage_artifact mv logs.tar.gz
stage_artifact cp build/buildhistory/images/*/glibc/genivi-dev-platform/files-in-image.txt
stage_artifact mv build/buildhistory
stage_artifact mv build/tmp/buildstats

if [[ "$LAYER_ARCHIVE" == "true" ]]; then
  tar cfj staging/meta-layers-snapshot.tar.bz2 meta-* poky renesas* build/conf
fi

# META-IVI note:
# usually we don't release built (binary) images of baseline -- however to keep
# the script consistent with GDP version of the script (and make releases
# possible in theory), the support is kept intact here:

# Environment contains alot of variables from Go.CD that specify the built
# version/hash, and other metadata.  Let's store them for future reference.
# and other relevant info
cd "$BASEDIR"
build_info_file=staging/build_info.txt

# Store environment info into log file for future reference
env >$build_info_file
echo 'Note the content of conf files and any local diffs reported below:' >>$build_info_file
git diff -- build/conf/*.conf build/conf/*.inc >>$build_info_file

# Environment variable moving selected parts from staging/ to release/
if [[ "$CREATE_RELEASE_DIR" == "true" ]]; then
  set +e
  mkdir -p release
  echo "Moving images to release/"
  mv staging/images release/ 2>/dev/null || true
  echo "Moving staging/sources to release/"
  cp -a staging/sources release/ 2>/dev/null
  echo "Moving staging/licenses into release/"
  mv staging/licenses release/
  echo "Copying various metadata to release"
  cp staging/files-in-image.txt release/ 2>/dev/null
  cp staging/build_info.txt release/ 2>/dev/null
  cp staging/license.manifest release/ 2>/dev/null
  set -e
fi

set +e
echo "Artifacts in staging/ and release/"
ls -al staging/ release/
echo
echo "...in release/images/ :"
ls -al release/images/

cleanup
true
