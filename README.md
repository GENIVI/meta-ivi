meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to be able to build a GENIVI compliant baseline
image.

Please see the MAINTAINERS file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.


Build a QEMU (ARMv7a) image including GENIVI P1 components
----------------------------------------------------------

You can build an QEMU image including GENIVI P1 components using the following steps:

1. Run the following command: $ source poky/oe-init-build-env

2. Add meta-ivi path to COREBASE/build/conf/bblayers.conf file.

3. Add meta-systemd path to COREBASE/build/conf/bblayers.conf file.

4. Set MACHINE ?= "vexpressa9" in COREBASE/build/conf/local.conf file.

5. Add BBMASK = "v4l-utils" in COREBASE/build/conf/local.conf file.

6. Add INCOMPATIBLE_LICENSE = "GPLv3" in COREBASE/build/conf/local.conf file.

7. Set DISTRO ?= "poky-ivi-systemd" in COREBASE/build/conf/local.conf file.

8. Optional: In COREBASE/build/conf/local.conf file, uncomment BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you have a quad core machine.

9. Download the GENIVI specific open source component from git.genivi.org (you need to have your GENIVI credential ready):
   - copy meta-ivi/scripts/dl_pkgs.sh into COREBASE/build/conf/
   $ ./conf/dl_pkgs.sh

10. Build discovery-image/excalibur-image including GENIVI 2.0 (Discovery/Excalibur) P1 components
   $ bitbake discovery-image

   or

   $ bitbake excalibur-image

11. Run the emulator:
   $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu discovery-image vexpressa9

   or

   $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu excalibur-image vexpressa9
