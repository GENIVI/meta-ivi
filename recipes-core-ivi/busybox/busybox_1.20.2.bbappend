PRINC := "${@int(PRINC) + 3}"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "file://defconfig"
