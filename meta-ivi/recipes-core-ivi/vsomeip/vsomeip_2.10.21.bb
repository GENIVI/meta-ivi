SUMMARY = "the implementation of SOME/IP"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost dlt-daemon"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "9fb9beecadf52083599302fa8ddee7efbec64a39"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https \
    file://0001-Support-boost-v1.66.patch \
    "
S = "${WORKDIR}/git"

inherit cmake lib_package gitpkgv

PACKAGES_remove += "${PN}-bin"
FILES_${PN} += "${bindir}/vsomeipd ${sysconfdir}/${BPN}"
FILES_${PN}-dev += "${libdir}/cmake"

BBCLASSEXTEND = "nativesdk"

do_install_append() {
    mv ${D}/usr/etc ${D}
}
