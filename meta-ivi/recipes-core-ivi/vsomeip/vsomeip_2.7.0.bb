SUMMARY = "the implementation of SOME/IP"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost dlt-daemon"

SRCREV = "fdf86239b4a6867e0a961a307649a6d5eb3a2f93"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https \
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
