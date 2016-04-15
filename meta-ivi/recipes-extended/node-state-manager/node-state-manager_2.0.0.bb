SUMMARY = "GENIVI Node State Manager"
DESCRIPTION = "The GENIVI Node State Manager \
		- is the central repository for information \
		- regarding the states/sessions \
		- inside the node. \
		"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "dd4a86b9459537d2e85489b36abf80f34d12f098"
SRC_URI = "git://git.projects.genivi.org/lifecycle/${PN}.git \
           file://nsm-fix-systemd-service-dep.patch \
           file://nsm-fix-no-libsystemd-daemon.patch \
           file://link-with-gio.patch \
          "
S = "${WORKDIR}/git"

PR = "r1"

EXTRA_OECONF = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '', d)}"

DEPENDS = "dbus glib-2.0 dlt-daemon persistence-client-library systemd"

inherit pkgconfig autotools-brokensep systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "nodestatemanager-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"


do_configure_prepend() {
	mkdir -p m4
	mkdir -p NodeStateAccess/doc
	mkdir -p NodeStateAccess/generated
}

# .so files are the actual libraries
FILES_SOLIBSDEV = ""
SOLIBS = "${SOLIBSDEV}"

FILES_${PN} += "\
    ${datadir}/dbus-1/system-services \
    ${datadir}/dbus-1/interfaces/org.genivi.NodeStateManager.*.xml \
    ${systemd_unitdir}/system/nodestatemanager-daemon.service \
    "

do_install_append() {
   rm -f ${D}${bindir}/NodeStateTest
   rm -f ${D}${libdir}/libNodeStateMachineTest.*
   rm -f ${D}${datadir}/dbus-1/interfaces/org.genivi.NodeStateMachineTest.xml
}
