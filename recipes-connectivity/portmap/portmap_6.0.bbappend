PRINC := "${@int(PRINC) + 4}"

# Find local ${PN} directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

inherit systemd

PACKAGES =+ "${PN}-systemd"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "portmap.service"
SYSTEMD_AUTO_ENABLE = "disable"

SRC_URI_append = " \
    file://portmap.service \
    "

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""
