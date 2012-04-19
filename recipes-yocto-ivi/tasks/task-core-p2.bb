DESCRIPTION = "P2 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

PACKAGES = "\
    task-core-p2 \
    task-core-p2-dbg \
    task-core-p2-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-core-p2 = "\
    pulseaudio \
    "



