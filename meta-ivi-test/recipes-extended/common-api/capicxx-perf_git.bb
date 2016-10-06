SUMMARY = "Common API C++ test code: measure the performance of message sending"
SECTION = "tests"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = \
    "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

SRCREV = "2eacc9d7fb76957e9a5450a559675fddf0a95ce2"
SRC_URI = "git://github.com/GENIVI/capic-poc.git;protocol=https \
    file://${BPN}_t.inc \
    "
S = "${WORKDIR}/git/test/capicxx-perf"

DEPENDS = "common-api-c++ common-api-c++-dbus capicxx-core-native capicxx-dbus-native"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=/usr"

_installdir = "/opt/tests/${PN}"

FILES_${PN} = " \
    ${_installdir}/* \
    "

FILES_${PN}-dbg += " \
    ${_installdir}/.debug/* \
    "

do_install_append() {
    _DEST=${D}${_installdir}
    install -d ${_DEST}
    mv ${D}/usr/bin/* ${_DEST}
    rmdir ${D}/usr/bin
    rmdir ${D}/usr

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}
