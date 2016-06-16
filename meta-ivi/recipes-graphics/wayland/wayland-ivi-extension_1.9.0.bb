SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "GENIVI Layer Management API based on Wayland IVI Extension"
HOMEPAGE = "http://projects.genivi.org/wayland-ivi-extension"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Wayland%20IVI%20Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

PR = "r1"

SRCREV = "beac5d2e25324ca9cc82d0e3b364512258af3567"
SRC_URI = "git://git.projects.genivi.org/${PN}.git;protocol=http \
    file://remove_compile-host-path_issue.patch \
    file://0001-buildsystem-make-example-programs-link-to-the-genera.patch \
    "
S = "${WORKDIR}/git"

DEPENDS = "weston"

inherit cmake autotools

EXTRA_OECMAKE := "-DWITH_ILM_INPUT=1"

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
