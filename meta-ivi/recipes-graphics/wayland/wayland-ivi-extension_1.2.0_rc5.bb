SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "LayerManagement API based on Wayland IVI Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

SRCREV = "4358cb8cb89d71d64c43c6e8607ad74f0ffe850a"

SRC_URI = "git://git.projects.genivi.org/wayland-ivi-extension.git \
           file://use_sysroot_dir.patch \
          "

inherit cmake autotools

DEPENDS = "weston"

S = "${WORKDIR}/git"

PR = "r0"

#WESTON_LIBDIR=

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
