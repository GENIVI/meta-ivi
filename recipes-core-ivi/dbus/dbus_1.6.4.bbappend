PRINC = "1"

# Don't register init scripts
INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

# Remove init scripts
do_install_append() {
	rm -rf ${D}${sysconfdir}/init.d
}

systemd_unitdir = "${libdir}/systemd"

