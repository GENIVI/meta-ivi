#
# test
#
do_install_append() {
   install -d ${D}/opt/tests/${PN}
   mv ${S}/test/pers_svc_test/.libs/pers_admin_test_framework \
       ${D}/opt/tests/${PN}
   install -d ${D}/Data
   install -m 0644 ${S}/test/pers_svc_test/webtool_export/resource2.tar.gz \
       ${D}/Data
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
    /opt/tests/${PN}/.debug/* \
    "
FILES_${PN}-test = " \
    /opt/tests/${PN}/* \
    /Data/resource2.tar.gz \
    "
