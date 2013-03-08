DESCRIPTION = "GENIVI 3.0 (Excalibur) P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r3"

inherit packagegroup

PACKAGES = "\
    packagegroup-excalibur-core-p1 \
    "

RDEPENDS_packagegroup-excalibur-core-p1 = "\
    node-startup-controller \
    obex-client \
    obexd \
    "
