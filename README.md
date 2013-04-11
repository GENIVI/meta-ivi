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
revision: a6502081b7c7acc49187aa7c68ad19e0751ee030

Using the above git sha's and master meta-ivi, bitbaking foton-image is
known to work (the foton-image build should be GENIVI 4.0 compliant).

For creating specific GENIVI compliant images, please make sure you git
checkout on the desired meta-ivi branch and follow the build instructions
located in the README.md file.

Build a QEMU image including GENIVI P1 components
--------------------------------------------------

You can build a QEMU image including GENIVI P1 components using the
following steps:

1. Run the following command:

   > $ source poky/oe-init-build-env

2. Add meta-ivi path to COREBASE/build/conf/bblayers.conf BBLAYERS variable.

3. Set MACHINE ??= "vexpressa9", MACHINE ??= "qemux86" or MACHINE ??= "qemux86-64"
in COREBASE/build/conf/local.conf file to build for an emulated ARMv7a, x86 or x86-64
instruction-set maschine respectively.

4. Add INCOMPATIBLE_LICENSE = "GPLv3" in COREBASE/build/conf/local.conf file.

5. Set DISTRO ?= "poky-ivi-systemd" in COREBASE/build/conf/local.conf file.

6. Optional: In COREBASE/build/conf/local.conf file, you may uncomment
BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you build on a
quad-core machine.

7. Build foton-image including GENIVI 4.0 (Foton) P1 components

   > $ bitbake foton-image

8. Run the emulator:

   > for qemu vexpressa9:
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu foton-image vexpressa9

   > for qemu x86:
   > $ PATH_TO_POKY/poky/scripts/runqemu foton-image qemux86

   > for qemu x86-64:
   > $ PATH_TO_POKY/poky/scripts/runqemu foton-image qemux86-64
