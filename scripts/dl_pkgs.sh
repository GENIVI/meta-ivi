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

GIT_PACKAGES=(layer-management DLT-daemon AudioManager)
GIT_URLS=(https://git.genivi.org/srv/git/layer_management https://git.genivi.org/srv/git/DLT-daemon https://git.genivi.org/srv/git/AudioManager)


echo -e "\nCreating download directory ..."
mkdir -p ../downloads/git2

echo -e "\nDownloading GENIVI packages ..."

#Download GIT Packages
for index in ${!GIT_PACKAGES[*]}
do
    echo -e "\nPACKAGE ${GIT_PACKAGES[$index]}"
    URLS2=${GIT_URLS[$index]/https:\/\/}
    URLS2=${URLS2//\//.}
    if [ ! -f downloads/${URLS2}.done ]
    then
        rm -rf downloads/git2/${URLS2}
        git clone -q --bare ${GIT_URLS[$index]} downloads/git2/${URLS2}
        if  [ $? == 0 ]
        then
    	    touch downloads/${URLS2}.done
    	fi
    else
	echo "${GIT_PACKAGES[$index]} already downloaded"
    fi
done
