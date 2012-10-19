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
branch: master
revision: ee8d3fb19fb0132812dec2bde1dfb7026deb642d

URI: git://git.openembedded.org/meta-openembedded
branch: master
revision: 83448c4b318ce60a54aae24a9a247456fa94caaf


Using the above git sha's and master meta-ivi, bitbaking excalibur-image is known to work (the excalibur-image build is Genivi 3.0 compliant).
For creating specific Genivi compliant images, please make sure you git checkout on the desired meta-ivi branch and follow the build instructions 
located in the README.md file.

Build a QEMU (ARMv7a) image including GENIVI P1 components
----------------------------------------------------------

You can build a QEMU image including GENIVI P1 components using the following steps:

1. Run the following command: $ source poky/oe-init-build-env

2. Add meta-ivi path to COREBASE/build/conf/bblayers.conf file.

3. Add meta-openembedded/meta-systemd path to COREBASE/build/conf/bblayers.conf file.

4. Add meta-openembedded/meta-oe path to COREBASE/build/conf/bblayers.conf file.

5. Set MACHINE ??= "vexpressa9" in COREBASE/build/conf/local.conf file.

6. Add BBMASK = "meta-systemd/meta-efl|meta-systemd/meta-gnome|meta-systemd/meta-multimedia" in COREBASE/build/conf/local.conf file.

7. Add INCOMPATIBLE_LICENSE = "GPLv3" in COREBASE/build/conf/local.conf file.

8. Set DISTRO ?= "poky-ivi-systemd" in COREBASE/build/conf/local.conf file.

9. Optional: In COREBASE/build/conf/local.conf file, uncomment BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you have a quad core machine.

10. Download the GENIVI specific open source component from git.genivi.org (you need to have your GENIVI credential ready):
   - copy meta-ivi/scripts/dl_pkgs.sh into COREBASE/build/conf/
   $ ./conf/dl_pkgs.sh

11. Build excalibur-image including GENIVI 3.0 (Excalibur) P1 components

   $ bitbake excalibur-image

12. Run the emulator:

   $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu excalibur-image vexpressa9
