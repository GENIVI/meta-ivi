
EXTRA_OECMAKE = " -DWITH_TESTS=ON -DUSE_BUILD_LIBS=OFF \
    -DTEST_EXECUTABLE_INSTALL_PATH=/opt/tests/${PN}/ \
    -Dgmock_build_tests=ON -Dgtest_build_samples=ON -Dgtest_build_tests=ON \
    -DCMAKE_SKIP_RPATH=ON \
    "

PACKAGES += " ${PN}-test"
do_install_append() {
    cd ${WORKDIR}/build
    install -d ${D}/opt/tests/${PN}
    for i in `find bin/ -type f -regex '.*[tT]est.*'`; do
      install -m 0755 ${i} ${D}/opt/tests/${PN}
    done
    install -m 0755 googleMock/gtest/libgtest_dll.so ${D}/opt/tests/${PN}
}

FILES_${PN}-dbg += " \
    /opt/tests/${PN}/.debug/* \
    "
FILES_${PN}-test = " \
    /opt/tests/${PN}/* \
    "
