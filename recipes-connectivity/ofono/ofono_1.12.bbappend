PRINC := "${@int(PRINC) + 4}"

inherit systemd

PACKAGES =+ "${PN}-systemd"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "ofono.service"
SYSTEMD_AUTO_ENABLE = "disable"

# Don't register init scripts
INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

# Remove init scripts
do_install_append() {
	case "${DISTRO_FEATURES}" in
		*systemd*) rm -rf ${D}${sysconfdir}/init.d/
	esac
}
