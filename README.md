meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to be able to build a GENIVI compliant baseline
image.

Please see the MAINTAINERS file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.


Layer dependencies
------------------

URI: git://git.yoctoproject.org/poky
branch: danny
revision: 27af23e65f63b41962a7cfddba9e9372d5212396

URI: git://git.openembedded.org/meta-openembedded
branch: master
revision: 5b427f6daae0caa7439256ecf35a877f1cb2dbed


Build a QEMU (ARMv7a) image including GENIVI P1 components
----------------------------------------------------------

You can build an QEMU image including GENIVI P1 components using the following steps:

1. Run the following command: $ source poky/oe-init-build-env

2. Add meta-ivi path to COREBASE/build/conf/bblayers.conf file.

3. Add meta-openembedded/meta-oe path to COREBASE/build/conf/bblayers.conf file.

4. Add meta-openembedded/meta-systemd path to COREBASE/build/conf/bblayers.conf file.

5. Set MACHINE ??= "vexpressa9" or MACHINE ??= "qemux86" in COREBASE/build/conf/local.conf file to build for emulated vexpressa9 or x86.

6. Add BBMASK = "meta-systemd/meta-efl|meta-systemd/meta-gnome|meta-systemd/meta-multimedia|meta-openembedded/meta-gnome/recipes-gnome|meta-systemd/meta-oe/recipes-support|meta-systemd/meta-networking" 
in COREBASE/build/conf/local.conf file.

7. Add INCOMPATIBLE_LICENSE = "GPLv3" in COREBASE/build/conf/local.conf file.

8. Set DISTRO ?= "poky-ivi-systemd" in COREBASE/build/conf/local.conf file.

9. Optional: In COREBASE/build/conf/local.conf file, uncomment BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you have a quad core machine.

10. Download the GENIVI specific open source component from git.genivi.org (you need to have your GENIVI credential ready):
   $ <meta-ivi-layer-path>/scripts/dl_pkgs.sh

11. Build excalibur-image including GENIVI 3.0 (Excalibur) P1 components

   $ bitbake excalibur-image

12. Run the emulator:

   $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu excalibur-image vexpressa9
