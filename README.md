meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to be able to build a GENIVI compliant baseline
image.

Please see the MAINTAINERS file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

You may in addition influence future meta-ivi development. For that take a
look at the [meta-ivi Trello board](https://trello.com/b/HplBZa2l) and vote.

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/poky
branch: master
revision: 93c04c16e45a3c8f60f8ffc0b26a78c24bda71da

URI: git://git.openembedded.org/meta-openembedded
branch: master
revision: 5056a7bf473c1e121eefa869f6a61ac4580604c9

Using the above git sha's and master meta-ivi, bitbaking excalibur-image is
known to work (the excalibur-image build should be GENIVI 3.0 compliant).

For creating specific GENIVI compliant images, please make sure you git
checkout on the desired meta-ivi branch and follow the build instructions
located in the README.md file.

Build a QEMU image including GENIVI P1 components
--------------------------------------------------

You can build a QEMU image including GENIVI P1 components using the
following steps:

1. Run the following command:

   > $ source poky/oe-init-build-env

2. Add meta-ivi path to COREBASE/build/conf/bblayers.conf file.

3. Add meta-openembedded/meta-systemd path to COREBASE/build/conf/bblayers.conf
file.

4. Add meta-openembedded/meta-oe path to COREBASE/build/conf/bblayers.conf file.

5. Add meta-openembedded/meta-gnome path to COREBASE/build/conf/bblayers.conf
file.

6. Add meta-openembedded/meta-networking path to
COREBASE/build/conf/bblayers.conf file.

7. Set MACHINE ??= "vexpressa9", MACHINE ??= "qemux86" or MACHINE ??= "qemux86-64"
in COREBASE/build/conf/local.conf file to build for an emulated ARMv7a, x86 or x86-64
instruction-set maschine respectively.

8. Add
BBMASK = "meta-systemd/meta-efl|meta-systemd/meta-gnome|\
meta-systemd/meta-multimedia|meta-systemd/meta-oe/recipes-support|\
meta-gnome/recipes-gnome"
in COREBASE/build/conf/local.conf file.

9. Add INCOMPATIBLE_LICENSE = "GPLv3" in COREBASE/build/conf/local.conf file.

10. Set DISTRO ?= "poky-ivi-systemd" in COREBASE/build/conf/local.conf file.

11. Optional: In COREBASE/build/conf/local.conf file, you may uncomment
BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you build on a
quad-core machine.

12. Build excalibur-image including GENIVI 3.0 (Excalibur) P1 components

   > $ bitbake excalibur-image

13. Run the emulator:

   > for qemu vexpressa9:
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu excalibur-image vexpressa9

   > for qemu x86:
   > $ PATH_TO_POKY/poky/scripts/runqemu excalibur-image qemux86

   > for qemu x86-64:
   > $ PATH_TO_POKY/poky/scripts/runqemu excalibur-image qemux86-64
