FILESEXTRAPATHS := "${THISDIR}/${PN}"
PRINC := "${@int(PRINC) + 3}"

# add support for GENIVI AF_Bus D-Bus Optimization
# - http://projects.genivi.org/afbus-dbus-optimization/
SRC_URI_AFBUS = "file://dbus_1.6-add-afbus-support.patch"

# add support for GENIVI CommonAPI D-Bus runtime
# - http://projects.genivi.org/commonapi/
SRC_URI_COMMONAPI = "file://dbus-DBusMessage-add-support-for-custom-marshaling.patch"

SRC_URI_append = "\
    ${SRC_URI_AFBUS} \
    ${SRC_URI_COMMONAPI} \
"

