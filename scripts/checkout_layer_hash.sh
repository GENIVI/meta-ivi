#!/bin/sh
# (C) 2015-12-12 Gunnar Andersson
# (C) 2016-10-25 Yong-iL Joh
# License: MPLv2

fail() {
   echo "FAILED."
   [ -n "$1" ] && echo "Message: " $@
   exit 1
}

# Store the location of these scripts
_DIR=$(dirname "$0")
META_IVI=$(readlink -f $_DIR/..)
MYDIR=$(readlink -f $_DIR)

layer=$1
[ -z "$layer" ] && fail "No layer name given"

# Extract revision from README in meta-ivi
cd "$META_IVI" || fail "Can't cd to meta-ivi directory"
revision=$($MYDIR/get_layer_info_from_README.sh $layer revision)
uri=$($MYDIR/get_layer_info_from_README.sh $layer URI)

# Fail if we could not fetch revision
[ -z "$revision" ] && fail "Revision extracted from README was empty"

# Checkout the right revision in this layer
if [ ! -d "$META_IVI/../$layer/.git" ]; then
   if [ -e "$META_IVI/../$layer" ]; then
      rm -rf "$META_IVI/../$layer"
   else
      cd "$META_IVI/.."
      git clone $uri
   fi
fi
cd "$META_IVI/../$layer" || fail "Can't cd to layer directory ($layer)"
git checkout $revision
