DESCRIPTION = "GENIVI P2 Abstract components (SC)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    packagegroup-abstract-component-p2 \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    node-state-manager \
    ofono \
    ofono-systemd \
    "
