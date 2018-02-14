#
# for testing
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append += " \
    file://0001-disable-sanitizers.patch \
    "

DEPENDS += " gtest"
EXTRA_OECMAKE += " -DBUILD_ILM_API_TESTS=1 -DINSTALL_ILM_API_TESTS=1"

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   mv ${D}/${bindir}/*test ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN}/* \
   "
