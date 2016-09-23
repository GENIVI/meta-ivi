#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " \
    file://${BPN}_t.inc \
    "

do_compile_append() {
   make -C NodeStateMachineTest
}

do_install_append() {
   install -d ${D}/opt/tests/${PN}/lib
   install -m 0755 ${S}/NodeStateMachineTest/NodeStateTest ${D}/opt/tests/${PN}
   install -m 0644 ${S}/NodeStateMachineTest/org.genivi.NodeStateMachineTest.xml \
     ${D}${datadir}/dbus-1/interfaces/
   install -m 0755 ${S}/NodeStateMachineTest/.libs/libNodeStateMachineTest.so \
     ${D}/opt/tests/${PN}/lib

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   /opt/tests/${PN}/lib/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN}/ \
   /opt/tests/${PN}/lib/libNodeStateMachineTest.so \
   ${datadir}/dbus-1/interfaces/org.genivi.NodeStateMachineTest.xml \
   "
