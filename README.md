meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to be able to build a GENIVI compliant baseline
image.

Please see the MAINTAINERS file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

You may in addition influence future meta-ivi development. For that take a
look at the [meta-ivi Trello board](https://trello.com/b/HplBZa2l) and vote.

Layer dependencies
------------------

URI: git://git.yoctoproject.org/poky  
branch: danny  
revision: bf909b267498dbab4d7695c26b0dce903ac8b6b0

URI: git://git.openembedded.org/meta-openembedded  
branch: danny  
revision: f026e96295da101bb715858bc8705de7e4a33992


Build a QEMU image including GENIVI P1 components
--------------------------------------------------

You can build an QEMU image including GENIVI P1 components using the
following steps:

1. Run the following command: $ source poky/oe-init-build-env

2. Add meta-ivi path to COREBASE/build/conf/bblayers.conf file.

3. Add meta-openembedded/meta-oe path to COREBASE/build/conf/bblayers.conf file.

4. Add meta-openembedded/meta-systemd path to COREBASE/build/conf/bblayers.conf file.

5. Set MACHINE ??= "vexpressa9" or MACHINE ??= "qemux86" in
COREBASE/build/conf/local.conf file to build for an emulated ARMv7a or IA-32
instruction-set maschine respectively.

6. Add BBMASK = "meta-systemd/meta-efl|meta-systemd/meta-gnome|meta-systemd/meta-multimedia|meta-openembedded/meta-gnome/recipes-gnome|meta-systemd/meta-oe/recipes-support|meta-systemd/meta-networking" 
in COREBASE/build/conf/local.conf file.

7. Add INCOMPATIBLE_LICENSE = "GPLv3" in COREBASE/build/conf/local.conf file.

8. Set DISTRO ?= "poky-ivi-systemd" in COREBASE/build/conf/local.conf file.

9. Optional: In COREBASE/build/conf/local.conf file, you may uncomment
BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you build on a
quad-core machine.

10. this step was made obsolete

11. Build excalibur-image including GENIVI 3.0 (Excalibur) P1 components

   > $ bitbake excalibur-image

12. Run the emulator:

   > for qemu vexpressa9:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu excalibur-image vexpressa9

   > for qemux86:  
   > $ PATH_TO_POKY/poky/scripts/runqemu excalibur-image qemux86
