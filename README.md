meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to make the Yocto Project reference system
Poky GENIVI compliant.

Please see the
[MAINTAINERS](http://git.yoctoproject.org/cgit/cgit.cgi/meta-ivi/tree/MAINTAINERS)
file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

The meta-ivi project welcomes contributions. You can contribute code,
submit patches, report bugs, answer questions on our mailing lists and
review and edit our documentation and much more.

Subscribe to the mailing list
    [here](https://lists.genivi.org/mailman/listinfo/genivi-meta-ivi).
View or Report bugs
    [here](https://bugs.genivi.org/buglist.cgi?product=meta-ivi).
Read or Edit the wiki
    [here](http://wiki.projects.genivi.org/index.php/meta-ivi).
Vote or Comment on our plan
    [here](https://trello.com/b/HplBZa2l/meta-ivi-development).
For information about the Yocto Project, see the
    [Yocto Project website](https://www.yoctoproject.org).
For information about the Yocto GENIVI Baseline, see the
    [Yocto GENIVI Baseline website](http://projects.genivi.org/GENIVI_Baselines/meta-ivi).

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/poky
> branch:   master
> revision: 199b1a8c7d7109d9b59d67bf4dcfeb03be07587a

Using the above git sha's and master meta-ivi, bitbaking gemini-image is
known to work (the gemini-image build should be GENIVI 5.0 compliant).

For creating a specific GENIVI compliant image version, please make sure you
git checkout the related meta-ivi branch and follow the build instructions
located in the README.md file of that branch.  So for example, to build
an image that should be GENIVI 4.0 compliant, checkout the meta-ivi 4.0 branch,
and follow the README.md part of that branch.  As does the GENIVI Alliance
we only support the current and the previous version.  Any version older
than that is not supported any more, and therefore may not build or run.

Supported Machines
------------------

We do smoke test the builds of the three machines that we currently support:

* QEMU (ARMv7) - emulated machine: vexpressa9
* QEMU (IA-32) - emulated machine: qemux86
* QEMU (x86-64) - emulated machine: qemux86-64

Please check on our [wiki](http://wiki.projects.genivi.org/index.php/meta-ivi)
regarding any community supported machines.
For example there Renesas provides a public Board Support Package (BSP)
available for use with meta-ivi.

Build a QEMU image that contains GENIVI components
--------------------------------------------------

You can build a QEMU image that should be GENIVI compliant using the
following steps:

1. Run the following command:
   > $ source poky/oe-init-build-env

2. Add the meta-ivi and meta-ivi-bsp path to the $BUILDDIR/conf/bblayers.conf
BBLAYERS variable.

3. Set MACHINE ??= "vexpressa9", MACHINE ??= "qemux86" or MACHINE ??= "qemux86-64"
in $BUILDDIR/conf/local.conf file to build for an emulated ARMv7a, x86 or x86-64
instruction-set maschine respectively.

4. Add INCOMPATIBLE_LICENSE = "GPLv3" in $BUILDDIR/conf/local.conf file.

5. Set DISTRO ?= "poky-ivi-systemd" in $BUILDDIR/conf/local.conf file.

6. Optional: In $BUILDDIR/conf/local.conf file, you may uncomment
BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you build on a
quad-core machine.

7. Build gemini-image including GENIVI 5.0 (Gemini) components
   > $ bitbake gemini-image

8. Run the emulator:
   > for qemu vexpressa9:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu gemini-image vexpressa9

   > for qemu x86:  
   > $ PATH_TO_POKY/poky/scripts/runqemu gemini-image qemux86

   > for qemu x86-64:  
   > $ PATH_TO_POKY/poky/scripts/runqemu gemini-image qemux86-64

9. To login use these credentials:
   > User - root
   > Password - root

