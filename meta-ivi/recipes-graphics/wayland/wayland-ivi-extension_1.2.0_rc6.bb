SUMMARY = "Wayland IVI Extension"
DESCRIPTION = "GENIVI Layer Management API based on Wayland IVI Extension"
HOMEPAGE = "http://projects.genivi.org/wayland-ivi-extension"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Wayland%20IVI%20Extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=176cedb32f48dd58f07e0c1c717b3ea4"

DEPENDS = "weston"

PV = "1.2.0-rc6+git${SRCPV}"

SRC_URI = "git://git.projects.genivi.org/wayland-ivi-extension.git \
           file://use_sysroot_dir.patch \
           file://fix_compositor_include.patch \
          "
SRCREV  = "19788f888163cdf4d16ee6bb0ad090a34b974477"
S = "${WORKDIR}/git"

inherit cmake autotools

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"

