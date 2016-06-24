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

PR = "r4"

SRCREV = "717e743c84ef9c168501dcbc012c4212f1903581"
SRC_URI = "git://git.projects.genivi.org/lifecycle/${BPN}.git;branch=genivi-excalibur;protocol=http \
           file://use-systemd-unit-dir.patch \
           file://fix-no-libsystemd-daemon.patch \
          "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 dlt-daemon systemd"

inherit autotools gtk-doc systemd
do_configure_prepend () {
        mkdir -p ${S}/m4
}

PACKAGES =+ "${PN}-nsm-dummy ${PN}-nsm-dummy-dbg"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "node-startup-controller.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} += "\
    ${libdir}/${BPN}-1/${BPN} \
    ${libdir}/${BPN}-1/legacy-app-handler \
    ${datadir}/dbus-1/system-services/org.genivi.NodeStartupController1.service \
    ${sysconfdir}/dbus-1/system.d/org.genivi.NodeStartupController1.conf \
    ${systemd_unitdir}/system/node-startup-controller.service \
    "

FILES_${PN}-dbg += "\
    ${libdir}/${BPN}-1/.debug/*ler \
    "

FILES_${PN}-nsm-dummy = "\
    ${libdir}/${BPN}-1/nsm-dummy \
    ${datadir}/dbus-1/system-services/org.genivi.NodeStateManager.* \
    ${sysconfdir}/dbus-1/system.d/org.genivi.NodeStateManager.conf \
    ${systemd_unitdir}/system/nsm-dummy.service \
    "

FILES_${PN}-nsm-dummy-dbg = "\
    ${libdir}/${BPN}-1/.debug/nsm-dummy \
    "
