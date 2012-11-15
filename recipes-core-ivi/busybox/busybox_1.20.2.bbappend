PRINC := "${@int(PRINC) + 2}"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "file://defconfig"
