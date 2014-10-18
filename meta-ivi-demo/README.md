meta-ivi-demo, the Yocto layer for the GENIVI Demo Platform
===========================================================

This layer's purpose is to provide a GENIVI Demo Platform (GDP) reference
image build. The layer supports cross-architecture application development
using QEMU emulation and a SDK.

Please see the
[MAINTAINERS](http://git.yoctoproject.org/cgit/cgit.cgi/meta-ivi/tree/MAINTAINERS)
file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

The GENIVI Demo Platform project welcomes contributions. You can contribute code,
submit patches, report bugs, answer questions on our mailing lists and
review and edit our documentation and much more.

Subscribe to the mailing list
    [here](https://lists.genivi.org/mailman/listinfo/genivi-meta-ivi).
View or Report bugs
    [here](https://bugs.genivi.org/buglist.cgi?product=meta-ivi).
Read or Edit the wiki
    [here](http://wiki.projects.genivi.org/index.php/meta-ivi).
For information about the Yocto Project, see the
    [Yocto Project website](https://www.yoctoproject.org).
For information about the Yocto GENIVI Baseline, see the
    [Yocto GENIVI Baseline website](http://projects.genivi.org/GENIVI_Baselines/meta-ivi).
For information about the Yocto GENIVI Demo Platform, see the
    [Yocto GENIVI Demo Platform website](http://projects.genivi.org/).

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/meta-ivi
> branch:   7.0
> revision: 

URI: git://git.yoctoproject.org/meta-renesas/meta-rcar-gen2
> branch:   master
> revision: 

URI: https://github.com/meta-qt5/meta-qt5.git
> branch:   master
> revision: 2f1b8fe630ac76c8ff5d184f109449b10fde5a92

URI: git://git.openembedded.org/meta-openembedded
> layers:   meta-oe, meta-ruby
> branch:   master
> revision: fa04f43dee6994bb63374995a6444e72d65bc658

URI: git://git.yoctoproject.org/poky
> branch:   dizzy
> revision: ccf5a4d2342c4fd40e4e0b332ea7e34e0bf175d4

URI: git://git.linaro.org/openembedded/meta-linaro.git
> branch: master
> revision: 7345c6089b2afbf65baf64224c14bbb9d38e2241

Supported Machines
------------------

We do support the builds for currently two machines:

* QEMU (x86-64) - emulated machine: qemux86-64
* Renesas R-Car Gen2 (R-Car M2) - machine: koelsch


When building for koelsch, add the following to your local.conf:

WAYLAND_ENABLE = "1"
GLES_ENABLE = "1"
WAYLAND_GFX_ENABLE = "1"
