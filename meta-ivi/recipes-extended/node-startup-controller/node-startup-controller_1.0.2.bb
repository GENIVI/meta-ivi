DEFAULT_PREFERENCE = "-1"

SUMMARY = "GENIVI Node Startup Controller"
DESCRIPTION = "The GENIVI Node Startup Controller \
		- manages the Last User Context (LUC) \
		- monitors node startup \
		  and \
		- manages legacy applications within a node \
		"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "717e743c84ef9c168501dcbc012c4212f1903581"
PR = "r3"

SRC_URI = "git://git.projects.genivi.org/lifecycle/node-startup-controller.git;branch=genivi-excalibur \
           file://use-systemd-unit-dir.patch \
           file://fix-no-libsystemd-daemon.patch \
          "

DEPENDS = "glib-2.0 dlt-daemon systemd"

S = "${WORKDIR}/git"

inherit autotools gtk-doc systemd
do_configure_prepend () {
        mkdir -p m4
}

PACKAGES =+ "${PN}-nsm-dummy ${PN}-nsm-dummy-dbg"

SYSTEMD_SERVICE = "node-startup-controller.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} += "\
    ${libdir}/${PN}-1/${PN} \
    ${libdir}/${PN}-1/legacy-app-handler \
    ${datadir}/dbus-1/system-services/org.genivi.NodeStartupController1.service \
    ${sysconfdir}/dbus-1/system.d/org.genivi.NodeStartupController1.conf \
    ${systemd_unitdir}/system/node-startup-controller.service \
    "

FILES_${PN}-dbg += "\
    ${libdir}/${PN}-1/.debug/*ler \
    "

FILES_${PN}-nsm-dummy = "\
    ${libdir}/${PN}-1/nsm-dummy \
    ${datadir}/dbus-1/system-services/org.genivi.NodeStateManager.* \
    ${sysconfdir}/dbus-1/system.d/org.genivi.NodeStateManager.conf \
    ${systemd_unitdir}/system/nsm-dummy.service \
    "

FILES_${PN}-nsm-dummy-dbg = "\
    ${libdir}/${PN}-1/.debug/nsm-dummy \
    "
