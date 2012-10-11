PRINC = "2"

# Depend on systemd if DISTRO_FEATURES contains systemd
DEPENDS += " ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} "

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

PACKAGES =+ " ${PN}-systemd "
FILES_${PN}-systemd += "${systemd_unitdir}/system"

# Systemd service needs systemd
RDEPENDS_${PN}-systemd = "systemd"
