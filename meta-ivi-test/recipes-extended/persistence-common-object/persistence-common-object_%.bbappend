#
# for test
#
EXTRA_OECONF += "--enable-tests"

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/test_pco_key_value_store \
       ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
RDEPENDS_${PN}-test += " libcheck"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   "

FILES_${PN}-test = " \
   /opt/tests/${PN}/test_pco_key_value_store \
   "
