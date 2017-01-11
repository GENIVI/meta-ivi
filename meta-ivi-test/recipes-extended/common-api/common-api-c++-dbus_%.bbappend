#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " \
    file://${BPN}_t.inc \
    "
SRC_URI_append += " \
    http://googletest.googlecode.com/files/gtest-1.7.0.zip \
    "
SRC_URI[md5sum] = "2d6ec8ccdf5c46b05ba54a9fd1d130d7"
SRC_URI[sha256sum] = "247ca18dd83f53deb1328be17e4b1be31514cedfc1e3424f672bf11fd7e0d60d"

EXTRA_OECMAKE += "-DWITH_DLTTEST=ON -DCMAKE_SKIP_RPATH=ON -DWITH_MAN=OFF \
    -DGTEST_ROOT=${WORKDIR}/gtest-1.7.0 \
    "

do_compile_append() {
   make -C src/test
}

do_install_append() {
   _BIN=${WORKDIR}/build/src/test
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${_BIN}/DBus*Test ${D}/opt/tests/${PN}

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/ "
