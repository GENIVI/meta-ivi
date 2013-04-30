PRINC := "${@int(PRINC) + 2}"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

do_install_append() {
	# Remove init scripts
	rm -r ${D}${sysconfdir}/init.d
}
