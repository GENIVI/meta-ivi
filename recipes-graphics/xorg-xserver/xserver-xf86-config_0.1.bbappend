# Append path for meta-ivi layer to include bsp xorg.conf
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"
