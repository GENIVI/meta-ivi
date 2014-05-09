# Use local $PN directory
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# add support for GENIVI AF_Bus D-Bus Optimization
# - http://projects.genivi.org/afbus-dbus-optimization/
SRC_URI_AFBUS = ""
# currently disabled in preparation of the move to kdbus
# uncomment the next line to reenable
#SRC_URI_AFBUS = "file://eglibc_2.18_add_af_bus_support.patch"

SRC_URI_append = "\
    ${SRC_URI_AFBUS} \
"

