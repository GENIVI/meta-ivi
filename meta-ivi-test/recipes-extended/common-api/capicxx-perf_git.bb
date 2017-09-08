SUMMARY = "Common API C++ test code: measure the performance of message sending"
SECTION = "tests"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = \
    "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

SRCREV = "2eacc9d7fb76957e9a5450a559675fddf0a95ce2"
SRC_URI = "git://github.com/GENIVI/capic-poc.git;protocol=https \
    file://${BPN}_t.inc \
    "
S = "${WORKDIR}/git/test/capicxx-perf"

DEPENDS = "common-api-c++ common-api-c++-dbus capicxx-core-native capicxx-dbus-native"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=/usr"

_installdir = "/opt/tests/${PN}"

FILES_${PN} = " \
    ${_installdir}/* \
    "

FILES_${PN}-dbg += " \
    ${_installdir}/.debug/* \
    "

do_configure_prepend() {
    capi_core_home=$(dirname `find ${WORKDIR}/recipe-sysroot-native -name commonapi-generator-linux-x86`)
    capi_dbus_home=$(dirname `find ${WORKDIR}/recipe-sysroot-native -name commonapi-dbus-generator-linux-x86`)
    if [ -L /usr/bin/java ]; then
       java_bin=$(readlink -f /usr/bin/java)
       if [ -L $java_bin ]; then
       	 J_HOME=$(readlink -f $java_bin | sed "s:/bin/java::")
       else
       	 J_HOME=$(readlink -f /usr/bin/java | sed "s:/bin/java::")
       fi
       rm -f ${capi_core_home}/jre ${capi_dbus_home}/jre
       ln -s ${J_HOME} ${capi_core_home}/
       ln -s ${J_HOME} ${capi_dbus_home}/
    else
       echo "hello. it's impossible"
    fi
}

do_install_append() {
    _DEST=${D}${_installdir}
    install -d ${_DEST}
    mv ${D}/usr/bin/* ${_DEST}
    rmdir ${D}/usr/bin
    rmdir ${D}/usr

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}
