# Use local $PN directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

# Add patch to avoid overwriting libc_cv_rootsbindir
SRC_URI += " \
             file://eglibc_2.16_add_af_bus_support.patch \
           "
