SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "GENIVI Layer Management API based on Wayland IVI Extension"
HOMEPAGE = "http://projects.genivi.org/wayland-ivi-extension"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Wayland%20IVI%20Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

DEPENDS = "weston"

# FIX ME
# This should be TAG = "${PV}" but yocto doesn't support lightweight tags for now
# https://bugzilla.yoctoproject.org/show_bug.cgi?id=6881
TAG = "a4bde3e5a4c21ff7cf28cc027d45d7c63cac2f66"
SRC_URI = "git://git.projects.genivi.org/${PN}.git;tag=${TAG} \
           file://use_sysroot_dir.patch \
           file://fix_compositor_include.patch \
          "
S = "${WORKDIR}/git"

inherit cmake autotools

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
