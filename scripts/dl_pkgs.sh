#!/bin/bash

# Copyright (c) 2012, Wind River Systems, Inc.

# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.

# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

GIT_PACKAGES=(node-startup-controller)
GIT_URLS=(https://git.genivi.org/srv/git/node-startup-controller)

if [ -z "$BUILDDIR" ];
then
	echo -e "ERROR:\tOE build environment not set"
	echo -e "\tThis should be done by, for example:"
	echo -e "\t% source ./poky/oe-init-build-env"
	exit 1
else
	# figure out Where to place downloads (configurable in local.conf)
	eval `(cd $BUILDDIR && bitbake -e) | grep ^DL_DIR=`
fi

if [ -z "$DL_DIR" ];
then
	GIT_DL_DIR="$BUILDDIR/downloads/git2"
else
	GIT_DL_DIR="$DL_DIR/git2"
fi

echo -e "\nCreating GIT download directory ($GIT_DL_DIR) ..."
mkdir -p ${GIT_DL_DIR}
echo -e "Downloading GENIVI packages ..."

# Download GENIVI OSS Packages
for index in ${!GIT_PACKAGES[*]}
do
    echo -e "\nPACKAGE ${GIT_PACKAGES[$index]}"
    URLS2=${GIT_URLS[$index]/https:\/\/}
    URLS2=${URLS2//\//.}
    if [ ! -f ${GIT_DL_DIR}/../${URLS2}.done ]
    then
        rm -rf ${GIT_DL_DIR}/${URLS2}
	if [ -z "$GENIVI_USER" ];
	then
        	git clone -q --bare ${GIT_URLS[$index]} ${GIT_DL_DIR}/${URLS2}
	else
        	git clone -q --bare ${GIT_URLS[$index]//:\/\//:\/\/$GENIVI_USER:$GENIVI_PASS@} ${GIT_DL_DIR}/${URLS2}
	fi
        if  [ $? == 0 ]
        then
    	    touch ${GIT_DL_DIR}/../${URLS2}.done
    	fi
    else
	# echo "${GIT_PACKAGES[$index]} already downloaded"
	echo -e "\talready downloaded"
    fi
done
echo ""
