PRINC := "${@int(PRINC) + 2}"
FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI += "file://add-default-root-pass.patch \
           "
