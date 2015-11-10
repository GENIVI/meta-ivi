SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "GENIVI Layer Management API based on Wayland IVI Extension"
HOMEPAGE = "http://projects.genivi.org/wayland-ivi-extension"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Wayland%20IVI%20Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

DEPENDS = "weston"

SRC_URI = "git://git.projects.genivi.org/${PN}.git \
          "
SRCREV = "e9f2eb1d7ff75aa1643d546aed8d460ebf3b3530"

S = "${WORKDIR}/git"

inherit cmake autotools

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
