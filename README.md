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
> branch:   jethro
> revision: fc45deac89ef63ca1c44e763c38ced7dfd72cbe1

URI: git://git.openembedded.org/meta-openembedded
> layer:    meta-oe
> branch:   jethro
> revision: ad6133a2e95f4b83b6b3ea413598e2cd5fb3fd90

Using the above git sha's and the master meta-ivi branch, bitbaking leviathan-image
is known to work (the leviathan-image build should be aligned with GENIVI 10.0).

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

3. Build leviathan-image including GENIVI 10.0 (Leviathan) components
   > $ bitbake leviathan-image

4. Run the emulator:
   > for qemu vexpressa9:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu leviathan-image vexpressa9
   >
   > for qemu x86:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu leviathan-image qemux86
   >
   > for qemu x86-64:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu leviathan-image qemux86-64

5. To login use these credentials:
   > User - root
   > Password - root


talk about using SRCREV instead of branch or tag at SRC_URI
-----------------------------------------------------------
Sent: Tuesday, April 05, 2016 11:33 PM
To: James Thomas
Cc: genivi-meta-ivi@lists.genivi.org
Subject: Re: [meta-ivi] Building with local source mirror

On Tue, Mar 29, 2016 at 10:51 PM, James Thomas <james.thomas@codethink.co.uk> wrote:
> One thing I noticed is that simply providing the SRCREV works as long 
> as that sha exists within master, if it doesn't then you have a build 
> error, so being able to use tags is useful.
>
> I think using git://...;tag=foo is not sufficient, because tags *can* 
> change (i.e there's no guarantee that the tag you're using is going to 
> be the same as the one you used yesterday).
>
> What would be nice is if you could go tag=foo, and have it verified 
> against SRCREV (in my testing this resulted in a build error *when* 
> the tag and sha matched)
>
> However, something like
>
> SRC_URI = "git://mygitrepo/foo.git;nobranch=1;branch=v0.2"
> SRCREV  = "7654321"
>
> does enforce that check (v0.2 is actually a tag in this case), which 
> seems to be pretty useful (the recipe provides something human 
> readable, and something a machine can understand, and will always 
> check they match)

I completely understand the reasoning behind this. The point I'm trying to make is that the automotive industry has a strong need for reproducible offline builds and any kind of mandatory online checks break this requirement. And like Federico said, using SRCREV is also the Yocto project practice.

If we want meta-ivi to be widely used in the industry I believe it should support it's needs. In my opinion the same should go for the whole GENIVI stack to work nicely, which in particular means tags of the projects should not change. But the easiest solution would be for meta-ivi to not use tags. That way it supports offline builds and it is also possible to track bugfixes in the projects instead of pinning to the tag and then getting the bugfixes in patch by patch until next release.

How do the others on this list feel about this proposal?

Regards,
--
Igor Socec
Software Engineer


