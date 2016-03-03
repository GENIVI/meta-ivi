#
# for test
#
EXTRA_OECONF += "--enable-tests"

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_test \
        ${D}/opt/tests/${PN}
   install -d ${D}/Data
   install -m 0644 ${S}/test/data/PAS_data.tar.gz \
        ${D}/Data/Data.tar.gz
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN}/persistence_client_library_test \
   /Data/Data.tar.gz \
   "
