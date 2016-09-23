#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " \
    file://0001-change-file-position-for-dlt-test-filetransfer.patch \
    file://${BPN}_t.inc \
    "

EXTRA_OECMAKE += "-DWITH_DLTTEST=ON -DCMAKE_SKIP_RPATH=ON -DWITH_MAN=OFF"

do_install_append() {
   _BIN=${WORKDIR}/build/src/tests
   install -d ${D}/opt/tests/${PN}/data
   install -m 0755 ${_BIN}/dlt-test-* ${D}/opt/tests/${PN}

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/ "
