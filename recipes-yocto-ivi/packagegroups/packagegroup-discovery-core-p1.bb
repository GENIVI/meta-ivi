DESCRIPTION = "2.0 (Discovery) P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"

inherit packagegroup

PACKAGES = "\
    packagegroup-discovery-core-p1 \
    packagegroup-discovery-core-p1-dbg \
    packagegroup-discovery-core-p1-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_packagegroup-discovery-core-p1 = "\
    ntp \
    ntp-systemd \
    qt4-embedded \
    usbutils \
    "



