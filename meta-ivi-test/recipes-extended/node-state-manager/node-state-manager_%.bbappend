#
# for test
#
do_install_append() {
   install -d ${D}/opt/tests/${PN}/lib
   install -m 0755 ${S}/NodeStateTest/NodeStateTest ${D}/opt/tests/${PN}
   install -m 0644 ${S}/NodeStateTest/org.genivi.NodeStateMachineTest.xml \
     ${D}${datadir}/dbus-1/interfaces/
   install -m 0755 ${S}/NodeStateTest/.libs/libNodeStateMachineTest.so \
     ${D}/opt/tests/${PN}/lib
}

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   /opt/tests/${PN}/lib/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN}/* \
   /opt/tests/${PN}/lib/libNodeStateMachineTest.so \
   ${datadir}/dbus-1/interfaces/org.genivi.NodeStateMachineTest.xml \
   "
