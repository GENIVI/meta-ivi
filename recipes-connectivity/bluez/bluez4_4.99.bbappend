PRINC = "2"

DEPENDS += " ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${base_libdir}/systemd/system/"

# Systemd service needs systemd
RDEPENDS_${PN}-systemd = "systemd"
