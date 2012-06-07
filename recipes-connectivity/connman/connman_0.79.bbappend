PRINC = "1"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${libdir}/systemd/"

do_install_append() {
	# Remove init scripts
	rm -r ${D}${sysconfdir}/init.d
}
