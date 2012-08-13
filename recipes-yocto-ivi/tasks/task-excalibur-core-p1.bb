DESCRIPTION = "Draft (Excalibur) P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r3"

# define preferred version(s)
PREFFERED_VERSION_node-startup-controller = "1.0.0"

PACKAGES = "\
    task-excalibur-core-p1 \
    task-excalibur-core-p1-dbg \
    task-excalibur-core-p1-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-excalibur-core-p1 = "\
    AudioManager \
    node-startup-controller \
    obex-client \
    obexd \
    "
