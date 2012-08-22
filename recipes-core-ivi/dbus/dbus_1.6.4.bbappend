FILESEXTRAPATHS := "${THISDIR}/${PN}"
PRINC = "1"

SRC_URI += "file://dbus_1.6-add-afbus-support.patch"

# Don't register init scripts
INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

# Remove init scripts
do_install_append() {
	rm -rf ${D}${sysconfdir}/init.d
}

systemd_unitdir = "${libdir}/systemd"

