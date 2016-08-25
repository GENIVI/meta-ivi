#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " \
    file://${BPN}_t.inc \
    "

do_compile_append() {
   make -C tst nhm-main-test
   make -C tst nhm-systemd-test
}

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${S}/tst/nhm-main-test ${D}/opt/tests/${PN}
   install -m 0755 ${S}/tst/nhm-systemd-test ${D}/opt/tests/${PN}
   ln -s /etc ${D}/opt/tests/cfg

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN}/ \
   /opt/tests/cfg \
   "
