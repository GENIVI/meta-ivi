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
    [Yocto GENIVI Demo Platform website](http://wiki.projects.genivi.org/index.php/Intrepid_-_Yocto_GENIVI_Demo_Platform).

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/meta-ivi
> branch:   7.0  
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

## The Renesas R-Car Gen2 (Koelsch) board depends on: ##

URI: git://git.yoctoproject.org/meta-ivi
> branch:   GDP-koelsch  
> revision: 1c52c69968ff02f3d72f67ab5f965ec439eabdb3

URI: https://github.com/meta-qt5/meta-qt5.git
> branch:   master  
> revision: 02861e677ab95b537efb331fb7faea4e5851d2ea

URI: git://git.openembedded.org/meta-openembedded
> layers:   meta-oe, meta-ruby  
> branch:   master  
> revision: 1513d0e31f609ffcde9a116a50bfb0360b9e7ecf

URI: git://git.yoctoproject.org/poky
> branch:   master  
> revision: fd0398f2c1355597a95242e6c8400eae6ad60fa4

URI: git://git.linaro.org/openembedded/meta-linaro.git
> branch: master  
> revision: 7345c6089b2afbf65baf64224c14bbb9d38e2241

URI: git://github.com/slawr/meta-renesas.git
> branch:   genivi-gdp  
> revision: e398b9fcacbad2f19ec673adda0f8dfda014fa12

URI: git://github.com/slawr/meta-renesas-proprietary.git
> branch:   genivi-gdp  
> revision: 4e276c8236d5daea7db4917f79ccd20086150009

Supported Machines
------------------

We do support the builds for currently two machines:

* QEMU (x86-64) - emulated machine: qemux86-64
* Renesas R-Car Gen2 (R-Car M2) - machine: koelsch


Miscellaneous
-------------

When building for koelsch, add the following to your local.conf:

> WAYLAND_ENABLE = "1"  
> GLES_ENABLE = "1"  
> WAYLAND_GFX_ENABLE = "1"

