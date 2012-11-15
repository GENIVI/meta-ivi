PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "file://defconfig"
