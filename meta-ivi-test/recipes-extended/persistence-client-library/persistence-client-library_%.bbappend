#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " file://${BPN}_t.inc \
    "

EXTRA_OECONF += " --enable-tests "

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_dbus_test \
        ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_test \
        ${D}/opt/tests/${PN}
   install -m 0755 ${S}/test/.libs/persistence_client_library_test_file \
        ${D}/opt/tests/${PN}
   install -d ${D}/Data/mnt-backup
   install -m 0644 ${S}/test/data/PAS_data.tar.gz \
        ${D}/Data/mnt-backup/
   install -m 0644 ${S}/test/data/PAS_data_benchmark.tar.gz \
        ${D}/Data/mnt-backup/

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/ /Data/* "
