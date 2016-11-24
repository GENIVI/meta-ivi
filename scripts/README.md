# Scripts

## checkout_layer_hash.sh

A build helper for meta-ivi.

This script extract the revision information for dependent layers (poky
and meta-openembedded) from the README of meta-ivi so that automated builds
are possible.

Usage:

Run in meta-ivi repository root (meta-ivi, not meta-ivi/meta-ivi).
The script assumes you have cloned poky and meta-openembedded inside
meta-ivi root which is normally not the case in a full system build - 
then meta-ivi is in parallel with all other layers, but when building
the baseline only, we assume the build happens inside of meta-ivi dir.


Example:
$ cd meta-ivi 
   (the first one)
$ checkout_layer_hash.sh poky
$ checkout_layer_hash.sh meta-openembedded

## Other scripts

* See README.md in project root
