meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to make the Yocto Project reference system
Poky GENIVI compliant.

Branch Policy
-------------

Please note:
- The most recent development is done on the highest numbered branch
- Master branch is used as a stable version, typically equal to the most recently *released* numbered branch
- Maintenance patches are added to both master and released branches

Contribution
-------------

The meta-ivi project welcomes contributions. You can contribute code,
submit patches, report bugs, answer questions on our mailing lists and
review and edit our documentation and much more.
[Wiki page](https://at.projects.genivi.org/wiki/display/PROJ/meta-ivi).
[Mailing list](https://lists.genivi.org/mailman/listinfo/genivi-meta-ivi).
[report Bugs](https://at.projects.genivi.org/jira/projects/BASE/).

Please see the
[MAINTAINERS](https://github.com/GENIVI/meta-ivi/blob/master/MAINTAINERS)
file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

Subscribe to the mailing list
    [here](https://lists.genivi.org/mailman/listinfo/genivi-meta-ivi).  
[View or Report bugs](https://at.projects.genivi.org/jira/secure/RapidBoard.jspa?rapidView=10&projectKey=BASE).
Read the [wiki](https://at.projects.genivi.org/wiki/display/PROJ/meta-ivi). 

For information about the Yocto Project, see the
[Yocto Project website](https://www.yoctoproject.org).  

For information about the Yocto GENIVI Baseline, see the
[Yocto GENIVI Baseline wiki](https://at.projects.genivi.org/wiki/display/PROJ/GENIVI+Baselines). 

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/poky
> branch:   pyro
> revision: 3f97cd3514f3e6025788ab7d6eec586fb7ac542f

URI: git://git.openembedded.org/meta-openembedded
> layer:    meta-oe
> branch:   pyro
> revision: 5e82995148a2844c6f483ae5ddd1438d87ea9fb7

URI: git://git.yoctoproject.org/meta-gplv2
> branch:   pyro
> revision: 07e5dd2136a2a7cc069ad8c70bb422fa9d5b14f0

Using the above git sha's and the master meta-ivi branch,
 bitbaking orion-image is known to work
 (the orion-image build should be aligned with GENIVI 13.0).

For creating a specific GENIVI compliant image version, please make sure you
git checkout the related meta-ivi branch and follow the build instructions
located in the README.md file of that branch.  So for example, to build
an image that should be GENIVI 9.0 compliant, checkout the meta-ivi 9.0 branch,
and follow the README.md part of that branch.  As does the GENIVI Alliance
we only support the current and the previous version.  Any version older
than that is not supported any more, and therefore may not build or run.

Supported Machines
------------------

We do smoke test the builds of the three machines that we currently support:

* QEMU (ARMv7) - emulated machine: vexpressa9
* QEMU (IA-32) - emulated machine: qemux86
* QEMU (x86-64) - emulated machine: qemux86-64
* QEMU (ARM64) - emulated machine: qemuarm64

Please check on our [wiki](https://at.projects.genivi.org/wiki/display/PROJ/meta-ivi)
regarding any community supported machines.
For example there Renesas provides a public Board Support Package (BSP)
available for use with meta-ivi.
