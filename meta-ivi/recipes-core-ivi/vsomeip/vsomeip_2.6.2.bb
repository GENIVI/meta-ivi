SUMMARY = "the implementation of SOME/IP"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost dlt-daemon"

SRCREV = "5315798ff81796217b55eb8c622f154bb0a1c487"
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