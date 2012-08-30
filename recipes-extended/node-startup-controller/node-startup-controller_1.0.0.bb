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

# tag 1.0.0 : c117ed6ce95229c5dfba33b890e7e7173218152e
SRC_URI = "git://git.genivi.org/srv/git/${PN};protocol=git;tag=c117ed6ce95229c5dfba33b890e7e7173218152e"
PR = "r1"

DEFAULT_PREFERENCE = "-1"

DEPENDS = "glib-2.0 DLT-daemon systemd"

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
