DESCRIPTION = "GENIVI P2 Specific components (SC)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    packagegroup-specific-component-p2 \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    common-api-c++ \
    common-api-c++-dbus \
    fuse \
    pulseaudio-server \
    wireless-tools \
    "

