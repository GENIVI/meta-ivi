meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to make the Yocto Project reference system
Poky GENIVI compliant.

Branch Policy
-------------

- New development is done on **master** branch and most new change requests
(PRs) should be proposed as changes to master, unless you know they are
applicable to a particular release only.
- Somewhere near a new major release, a numbered release branch (e.g. 14.0)
is created from master, and the new branch goes into stabilization/release phase.
- Major numbers are stepped up every new release.  The release schedule is
driven by a time plan.  Use [semantic versioning](http://semver.org/) for
minor and patch numbers.
- GENIVI-specific release tags, such as "M-0.2" for the second baseline
*pre*release for the "M" platform may also be added.
- After a versioned release, the release branch remains for maintenance and
updates.
- The project maintainer shall ensure that relevant patches are
cherry-picked to every branch where they apply. I.e. patches should be
back-ported *at minimum* to the Support Window versions as defined below.
- In general, prefer a linear commit history (rebase and cherry-pick),
  applying merge commits only where absolutely necessary to sort out a
  complex merge situation (which we should rarely have).

Support Window (Bugfixes, improvements)
---------------------------------------

- Because of available resources, and often low engagement from those companies
that have settled on a version and gone into a production project, support
is given for only the most recently released version, and *one* version
before it.

Support Window (Security)
-------------------------

- If critical security fixes are identified, the maintainer should apply
them (if applicable) to the most recently released version and *two*
versions before it.

**Note** however that there is currently no quantified or documented
commitment to tracking CVEs, nor any guarantee to apply all possible
security fixes.  While it is of course tracked to the best of the
maintainer's ability, the project is dependent on community input.  All the
_responsibility remains on the adopting companies_ to secure their final
products.

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
> branch:   rocko
> revision: 21ba45aa77e4d43a93cd96d859707a4758e8b64b

URI: git://git.openembedded.org/meta-openembedded
> layer:    meta-oe
> branch:   rocko
> revision: 6e3fc5b8d904d06e3aa77e9ec9968ab37a798188

URI: git://git.yoctoproject.org/meta-gplv2
> branch:   rocko
> revision: c7f97f199a6dd54d3f369f0465a6bfd2cfa739b9

Using the above git sha's and the master meta-ivi branch,
 bitbaking pulsar-image is known to work
 (the pulsar-image build should be aligned with GENIVI 14.0).

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
