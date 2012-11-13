# Use local $PN directory
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

# Add patch to avoid overwriting libc_cv_rootsbindir
SRC_URI += " \
             file://eglibc_2.16_add_af_bus_support.patch \
           "
