SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "LayerManagement API based on Wayland IVI Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

SRCREV = "c622cfdca7b917d2e4096e9f68d16ee6f9951fdd"

SRC_URI = "git://git.projects.genivi.org/wayland-ivi-extension.git \
           file://use_sysroot_dir.patch \
           file://fix_compositor_include.patch \
          "

inherit cmake autotools

DEPENDS = "weston"

S = "${WORKDIR}/git"

PR = "r1"

#WESTON_LIBDIR=

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
