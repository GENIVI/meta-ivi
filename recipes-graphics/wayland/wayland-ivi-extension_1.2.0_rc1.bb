SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "LayerManagement API based on Wayland IVI Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

SRCREV = "882e54daa1eafb56d0e7f0120db6bc36bd38d165"

SRC_URI = "git://git.projects.genivi.org/wayland-ivi-extension.git \
           file://use_sysroot_dir.patch \
          "

inherit cmake autotools

DEPENDS = "weston"

S = "${WORKDIR}/git"

#WESTON_LIBDIR=

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
