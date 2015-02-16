meta-ivi-demo, the meta-ivi demo layer
======================================

This layer's purpose is to provide a demo image build. The layer supports
cross-architecture application development using QEMU emulation and a SDK.

Please see the
[MAINTAINERS](http://git.yoctoproject.org/cgit/cgit.cgi/meta-ivi/tree/MAINTAINERS)
file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

We welcome contributions. You can contribute code, submit patches, report bugs,
answer questions on our mailing lists and review and edit our documentation
and much more.

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

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/meta-ivi
> branch:   7.0  
> revision: 

URI: git://git.openembedded.org/meta-openembedded
> layers:   meta-oe  
> branch:   dizzy  
> revision: 6413cdb66acf43059f94d0076ec9b8ad6a475b35

URI: git://git.yoctoproject.org/poky
> branch:   dizzy  
> revision: 9bef9b9dddfaa90cecea75937d97a8d96f006663

Supported Machines
------------------

We do support the builds for currently the following machines:

* QEMU (x86-64) - emulated machine: qemux86-64


Miscellaneous
-------------

For the QEMU machine, in order to have audio, the emulation should be done
like:

> QEMU_AUDIO_DRV=alsa runqemu ivi-image-demo qemux86-64 audio
