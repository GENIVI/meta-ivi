PRINC := "${@int(PRINC) + 4}"

inherit systemd

PACKAGES =+ "${PN}-systemd"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "bluetooth.service"
SYSTEMD_AUTO_ENABLE = "disable"
