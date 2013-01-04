PRINC = "3"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "ofono.service"
SYSTEMD_AUTO_ENABLE = "disable"

# Don't register init scripts
INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

# Remove init scripts
do_install_append() {
	if [[ "${DISTRO_FEATURES}" == *"systemd"*  ]]
	then
		rm -rf ${D}${sysconfdir}/init.d/
	fi
}
