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
LIC_FILES_CHKSUM = "file://COPYING;md5=88220bd246a0e7bb266ce0330a7b2bfa"

SRCREV = "34a421a9267df08d0e72037396b515fe31de5523"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https \
           file://use-systemd-unit-dir.patch \
          "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 dlt-daemon systemd glib-2.0-native"
RDEPENDS_${PN} = "node-state-manager"

inherit autotools gtk-doc systemd

do_configure_prepend () {
        mkdir -p ${S}/m4
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "node-startup-controller.service"
SYSTEMD_AUTO_ENABLE = "enable"

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
