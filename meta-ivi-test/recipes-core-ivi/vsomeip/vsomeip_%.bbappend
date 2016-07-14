#
# for test
#
#FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " \
    http://googletest.googlecode.com/files/gtest-1.7.0.zip \
    "
SRC_URI[md5sum] = "2d6ec8ccdf5c46b05ba54a9fd1d130d7"
SRC_URI[sha256sum] = "247ca18dd83f53deb1328be17e4b1be31514cedfc1e3424f672bf11fd7e0d60d"

EXTRA_OECONF += " -DTEST_IP_MASTER=10.0.3.1 -DTEST_IP_SLAVE=10.0.3.125"
do_configure_prepend() {
   export GTEST_ROOT=${WORKDIR}/gtest-1.7.0
}

do_compile_append() {
   perl -pi -e 's|-Wl,-rpath,/storage/.*||' examples/CMakeFiles/*/link.txt
   make examples

   perl -pi -e 's|-Wl,-rpath,/storage/.*||' test/CMakeFiles/*/link.txt
   make build_tests
}

do_install_append() {
   install -d ${D}/opt/tests/${PN}/examples
   install -m 0755 ${B}/examples/*-sample ${D}/opt/tests/${PN}/examples
   for i in sh json test client service; do
     cp -f ${B}/test/*${i} ${D}/opt/tests/${PN}
   done
}

PACKAGES += "${PN}-test"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN} \
   "
