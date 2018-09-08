DESCRIPTION = "Packagegroup for xserver and GPU drivers"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

XSERVER ?= "
    xf86-input-evdev \
    xf86-input-keyboard \
    xf86-input-mouse \
    xf86-video-fbdev \
    xserver-xorg \
"

RDEPENDS_${PN} = "${XSERVER}"
