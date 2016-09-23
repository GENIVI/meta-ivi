#
# test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " file://${BPN}_t.inc \
    "

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   mv ${S}/test/pers_svc_test/.libs/pers_admin_test_framework \
       ${D}/opt/tests/${PN}
   install -m 0644 ${S}/test/pers_svc_test/webtool_export/resource1.tar.gz \
       ${D}/opt/tests/${PN}
   install -m 0644 ${S}/test/pers_svc_test/webtool_export/resource2.tar.gz \
       ${D}/opt/tests/${PN}
   install -d ${D}/Data/mnt-wt/
   touch ${D}/Data/mnt-wt/README.wt
   install -d ${D}/Data/mnt-c/
   touch ${D}/Data/mnt-wt/README.c

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/ /Data/* "
