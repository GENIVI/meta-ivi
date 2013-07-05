FILESEXTRAPATHS := "${THISDIR}/${PN}"
PRINC := "${@int(PRINC) + 2}"

SRC_URI += "file://dbus_1.6-add-afbus-support.patch"

