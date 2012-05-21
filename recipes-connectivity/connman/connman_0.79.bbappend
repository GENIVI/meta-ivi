PRINC = "1"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "/lib/systemd/system/"

do_install_append() {
	# Remove init scripts
	rm -r ${D}${sysconfdir}/init.d
}
