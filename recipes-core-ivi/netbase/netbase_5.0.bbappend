PRINC := "${@int(PRINC) + 2}"

# Dont register init scripts
INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

# Remove installed init scripts
do_install_append () {
	rm -rvf ${D}${sysconfdir}/init.d
}
