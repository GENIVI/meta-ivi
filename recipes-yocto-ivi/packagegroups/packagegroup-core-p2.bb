DESCRIPTION = "P2 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    packagegroup-core-p2 \
    "

RDEPENDS_packagegroup-core-p2 = "\
    pulseaudio \
    ofono \
    ofono-systemd \
    node-state-manager \
    wireless-tools \
    "



