DESCRIPTION = "Packagegroup for xserver and GPU drivers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup

XSERVER ?= "xserver-xorg \
            xf86-input-evdev \
            xf86-input-mouse \
            xf86-video-fbdev \
            xf86-input-keyboard \
            "

RDEPENDS_${PN} = " \
                  ${XSERVER}"

PACKAGE_ARCH = "${MACHINE_ARCH}"