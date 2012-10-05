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

SRCREV = "958e5ab2bc93ac0d885ca75f4f33988cbdd3e758"
PV = "1.0.0+git${SRCPV}"
PR = "r0"

SRC_URI = "git://git.genivi.org/srv/git/${PN};protocol=git"

DEPENDS = "glib-2.0 dlt-daemon systemd"

S = "${WORKDIR}/git"

inherit autotools gtk-doc
do_configure_prepend () {
        mkdir m4
}

PACKAGES =+ "${PN}-nsm-dummy ${PN}-nsm-dummy-dbg"

RRECOMMENDS_${PN} += "${PN}-nsm-dummy"


FILES_${PN} += "\
	${libdir}/${PN}-1/${PN} \
	${libdir}/${PN}-1/legacy-app-handler \
	${libdir}/systemd/system/${PN}.service \
	${datadir}/dbus-1/system-services/org.genivi.NodeStartupController1.service \
	"
FILES_${PN}-dbg += "\
	${libdir}/${PN}-1/.debug/*ler \
	"
FILES_${PN}-nsm-dummy = "\
	${libdir}/${PN}-1/nsm-dummy \
	${libdir}/systemd/system/nsm-dummy.service \
	${datadir}/dbus-1/system-services/org.genivi.NodeStateManager.* \
	"
FILES_${PN}-nsm-dummy-dbg = "\
	${libdir}/${PN}-1/.debug/nsm-dummy \
	"
