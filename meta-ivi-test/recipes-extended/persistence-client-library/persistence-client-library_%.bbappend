#
# for test
#
#LOCALSTATEDIR
#localstatedir
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " file://${BPN}_t.inc \
    "

EXTRA_OECONF += " --enable-tests "

do_configure_append() {
    perl -pi -e "s|LOCALSTATEDIR|\"/opt/tests/${BPN}\"|" \
      ${S}/test/persistence_client_library_test.c
}

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_dbus_test \
        ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_test \
        ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_test_file \
        ${D}/opt/tests/${PN}
   mv ${D}/var/*.tar.gz ${D}/opt/tests/${PN}
   rmdir ${D}/var

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/ "
