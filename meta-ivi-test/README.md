# Testing GENIVI software in meta-ivi

## Introduction
The Yocto Baseline uses the meta-ivi layer to create a Genivi compliant build of the Yocto Project Poky reference distribution.
When the meta-ivi-test layer is included in a Yocto Baseline build it adds the extra packages needed to run tests for Genivi components.
Currently not all components are included and contribution to fill the gap is welcomed.

## Test instructions

### Build and booting with test image
add meta-ivi-test layers to <your build directory>/conf/bblayers.conf

```
BBLAYERS += " \
    ##your source code root##/poky/../meta-ivi/meta-ivi-test \
    "
```

build test-image

```
export TEMPLATECONF=`pwd`/meta-ivi/meta-ivi/conf
source poky/oe-init-build-env build
MACHINE=qemux86-64 bitbake test-image
```

boot with test-image

```
../meta-ivi/scripts/runqemu test-image qemux86-64 nographic
```

### Test run

* run all tests of all s/w

```
root@qemux86-64:~# /opt/tests/test-fw.sh
##########
audiomanager
##########
[ number of tests: 7 ]
[ run_1 ][ PASSED ] 19 tests.
...
```

* list up of specific s/w with tests

```
root@qemux86-64:~# /opt/tests/test-fw.sh list
audiomanager 7
audiomanagerplugins 5
capicxx-perf 1
common-api-test 1
dlt-daemon 2
...
```

* run all tests of specific s/w

```
root@qemux86-64:~# /opt/tests/test-fw.sh dlt-daemon
##########
dlt-daemon
##########
[ number of tests: 2 ]
[ run_1 ] PASSED
[ run_2 ] PASSED
...
```

* run a test of specific test

```
root@qemux86-64:~# /opt/tests/test-fw.sh dlt-daemon 1
##########
dlt-daemon
##########
[ run_1 ]
testFile1Run1 successful
testFile1Run2 successful
testFile2Run1 successful
...
```

### How to create test cases

* target directory of test program is /opt/tests/<s/w name>

```
root@qemux86-64:~# ls -F /opt/tests/
audiomanager/ node-startup-controller/
audiomanagerplugins/ node-state-manager/
bluez5/ persistence-administrator/
capicxx-perf/ persistence-client-library/
...
```

* create script for test-fw.sh
  * Filename should be /opt/tests/<s/w name>/<s/w name>_t.inc

```
root@qemux86-64:~# ls -F /opt/tests/audiomanager
AmControlInterfaceTest* AmRouterTest* AmTelnetServerTest*
AmMapHandlerTest* AmRoutingInterfaceTest* audiomanager_t.inc*
AmRouterMapTest* AmSocketHandlerTest*
root@qemux86-64:~#
root@qemux86-64:~#
root@qemux86-64:~#
root@qemux86-64:~#
root@qemux86-64:~# cat /opt/tests/audiomanager/audiomanager_t.inc
#!/bin/sh
get_list() {
return 2
}
get_title() {
echo "dlt-daemon"
}
_run_dir=/opt/tests/dlt-daemon
run_1() {
# If argument 1 is exist, please, reduce log messages
if [ "x${1}" != "x" ]; then
${_run_dir}/dlt-test-filetransfer > /tmp/t
_c1=$?
if [ "x${_c1}" = "x0" ]; then
echo " PASSED"
else
echo " FAILED"
fi
else
${_run_dir}/dlt-test-filetransfer
fi
}
run_2() {
if [ "x${1}" != "x" ]; then
...
}
script have to provide 3 functions at least
get_list() -> return the number of testcases
get_title() -> return the string of test title
actual test function. The name must be run_x() like
run_1()
run_2()
...
```

| Note...
| -------------------------------------------------------------------------
| In run_X() functions, If argument 1 is exist, please, reduce log messages

| Warning!
| ----------------------------------------------------------------------------- 
| DEPRECATED, this is as of this writing the same information as above but will be updated. Kept for reference for now. See test_of_meta-ivi.pptx for instructions on how to build, run and add new tests.

## build and test manually
| Warning! 
| -----------------------------------------------------------------------------
| This information is partially incorrect, we might want to remove it and/or move it into the git repository.

1. add meta-ivi-test layers to <your build directory>/conf/bblayers.conf

```
BBLAYERS += " \
    ##your source code root##/poky/../meta-ivi/meta-ivi-test \
    "
```

2. add test package to your image by modifying <your build directory>/conf/bblayers.conf.

```
IMAGE_INSTALL_append += " \
    audiomanager-test \
    common-api-test \
    "
```

3. after build, you can see the directory /opt/tests in your s/w image

```
root@qemux86-64:~# ls -F /opt/tests/
audiomanager/               node-startup-controller/
audiomanagerplugins/        node-state-manager/
bluez5/                     persistence-administrator/
capicxx-perf/               persistence-client-library/
common-api-test/            test-fw.sh*
```
4. run test with following instructions
    1. Test of AudioManager
    1. Test of wayland-ivi-extension
    1. Test of Common API C++ runtime.
    1. Test of persistence-administrator
    1. Test of persistence-client-library (PCL)
    1. Test of persistence-common-object
    1. Test of dlt-daemon
    1. Test of node-state-manager
    1. Test of node-health-monitor
    1. Test of node-startup-controller
    1. Test of bluez5
