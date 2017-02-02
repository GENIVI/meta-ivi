#!/bin/sh
# (C) Gunnar Andersson 2015-12-12
# License: MPLv2

# This fetches information about layer dependencies
# as documented in meta-ivi README.
# revision, branch name, or sub layer name can be extracted

README=README.md
DEBUG=false


fail() {
   echo "*** FAILED ***"
   [ -n "$1" ] && echo "Message: " $@
   exit 1
}

sanity_check_num_lines() {
   what=$1 ; min=$2 ; max=$3

   numlines="$(wc -l)" # < Reads from stdin

   if [ $numlines -lt $min ] ; then 
      fail "Sanity check: It looks like section $what has $numlines lines, and that is not enough.  Please check what is wrong."
   fi

   if [ $numlines -gt $max ] ; then
      fail "Sanity check: It looks like section $what has $numlines lines, and that is more than expected.  Please check what is wrong."
   fi
}

[ -f ./$README ] || fail "Cannot find README.md.  (Current directory is $PWD)"

get_layer_info() {
   layer=$1
   what=$2

   # Example README format:
   #
   #Layer Dependencies
   #------------------
   #
   #URI: git://git.yoctoproject.org/poky
   #> branch:   fido
   #> revision: eb4a134a60e3ac26a48379675ad6346a44010339
   #
   #URI: git://git.openembedded.org/meta-openembedded
   #> layer:    meta-oe
   #> branch:   fido
   #> revision: 5b0305d9efa4b5692cd942586fb7aa92dba42d59

   # Extract relevant section of the README
   dependency_section=$( <$README \
      sed -n '/Layer Dependencies/,/known to work/p' |\
      egrep 'URI:|branch:|layer:|revision:')

   # Debug printouts...
   $DEBUG && echo "OK, found the following layer info in dependency section:"
   $DEBUG && cat <<EOT
   $dependency_section
EOT

   cat <<EOT | sanity_check_num_lines "Layer Dependencies Section" 6 20
   $dependency_section
EOT

   # Get only this layer's information
   layer_info=$(
   cat <<EOT | sed -n "/URI:.*$layer/,/URI:/p" | sed 's/> //' | head -4
   $dependency_section
EOT
   )

   # Get value for item "what" (what = revision, branch, ...)
   value=$(
   cat <<EOT | grep "$what" | awk '{print $2}'
   $layer_info
EOT
   )

   # Check again - now only one line expected...
   cat <<EOT | sanity_check_num_lines "$what information for $layer" 1 1
   $value
EOT

   # OK, this should be it
   echo $value

}

checkoutlayer=$1
[ -z "$checkoutlayer" ] && { echo "Usage: $0 <layername, e.g. poky>" ; exit 1 ; }

# Extract revision from README in meta-ivi
revision=$(get_layer_info $checkoutlayer revision)

# Fail if we could not fetch revision
[ -z "$revision" ] && fail "Revision extracted from README was empty"

# Checkout the right revision in this layer.  We expect the layer, e.g.
# poky to be found inside the root of meta-ivi, that is the $PWD when
# this script is run.  (Not true on full system builds, but typically
# when building baseline only)
cd "$checkoutlayer" || fail "Can't cd to layer directory ($checkoutlayer)"
git checkout $revision

