DESCRIPTION = "Discovery P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

PACKAGES = "\
    task-discovery-core-p1 \
    task-discovery-core-p1-dbg \
    task-discovery-core-p1-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-discovery-core-p1 = "\
    AudioManager \
    "



