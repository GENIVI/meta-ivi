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
    [here](https://at.projects.genivi.org/jira/secure/RapidBoard.jspa?rapidView=10&projectKey=BASE).
Read or Edit the wiki
    [here](https://at.projects.genivi.org/wiki/display/PROJ/meta-ivi).
For information about the Yocto Project, see the
    [Yocto Project website](https://www.yoctoproject.org).  
For information about the Yocto GENIVI Baseline, see the
    [Yocto GENIVI Baseline website](http://projects.genivi.org/GENIVI_Baselines/meta-ivi).

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/poky
> branch:   krogoth
> revision: 12eb72ee3b02f826a156ff4e396c770f2b93571e

URI: git://git.openembedded.org/meta-openembedded
> layer:    meta-oe
> branch:   krogoth
> revision: 247b1267bbe95719cd4877d2d3cfbaf2a2f4865a

Using the above git sha's and the master meta-ivi branch,
 bitbaking miranda-image is known to work
 (the miranda-image build should be aligned with GENIVI 11.0).

For creating a specific GENIVI compliant image version, please make sure you
git checkout the related meta-ivi branch and follow the build instructions
located in the README.md file of that branch.  So for example, to build
an image that should be GENIVI 6.0 compliant, checkout the meta-ivi 6.0 branch,
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

1. Export TEMPLATECONF to pick up correct configuration for the build
export TEMPLATECONF=/full/path/to/meta-ivi/meta-ivi/conf

2. Run the following command:
   > $ source poky/oe-init-build-env

3. Build miranda-image including GENIVI 11.0 (Miranda) components
   > $ bitbake miranda-image

4. Run the emulator:
   > for qemu vexpressa9:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu miranda-image vexpressa9
   >
   > for qemu x86:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu miranda-image qemux86
   >
   > for qemu x86-64:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu miranda-image qemux86-64

5. To login use these credentials:
   > User - root
   > Password - root
