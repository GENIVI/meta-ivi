DESCRIPTION = "Packagegroup for xserver and GPU drivers"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

XSERVER ?= "xserver-xorg \
            xf86-input-evdev \
            xf86-input-mouse \
            xf86-video-fbdev \
            xf86-input-keyboard \
            "

RDEPENDS_${PN} = "${XSERVER}"
