#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " file://${BPN}_t.inc \
    "

EXTRA_OECONF += " --enable-tests "

do_configure_append() {
   perl -pi -e 's|usr/local/var|opt/tests/persistence-common-object|' \
     ${S}/test/test_pco_key_value_store.c
}

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/test_pco_key_value_store \
       ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_common_object_test \
       ${D}/opt/tests/${PN}

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
   mv ${D}/var/rct_compare.tar.gz ${D}/opt/tests/${PN}
   rmdir ${D}/var
}

PACKAGES += "${PN}-test"
RDEPENDS_${PN}-test += " libcheck"

FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/ "
