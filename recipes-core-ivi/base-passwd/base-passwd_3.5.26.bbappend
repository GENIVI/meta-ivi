PRINC := "${@int(PRINC) + 1}"
FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI += "file://add-default-root-pass.patch \
           "
