PRINC := "${@int(PRINC) + 4}"

FILESEXTRAPATHS := "${THISDIR}/${PN}"

inherit systemd
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "X.service"

SRC_URI_append = " file://X.service \
                 "
