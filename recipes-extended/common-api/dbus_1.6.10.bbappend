FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

SRC_URI += "file://dbus-DBusMessage-add-support-for-custom-marshaling.patch"

