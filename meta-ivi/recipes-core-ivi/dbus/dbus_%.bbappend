FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# add support for GENIVI AF_Bus D-Bus Optimization
# - http://projects.genivi.org/afbus-dbus-optimization/
SRC_URI_AFBUS = ""
# currently disabled in preparation of the move to kdbus
# uncomment the next line to reenable
#SRC_URI_AFBUS = "file://dbus_1.6-add-afbus-support.patch"

# add support for GENIVI CommonAPI D-Bus runtime
# - http://projects.genivi.org/commonapi/
SRC_URI_COMMONAPI = " \
    file://capi-dbus-add-send-with-reply-set-notify.patch \
    file://capi-dbus-add-support-for-custom-marshalling.patch \
    file://capi-dbus-block-acquire-io-path-on-send.patch \
    file://capi-dbus-correct-dbus-connection-block-pending-call.patch \
    "

SRC_URI_append = "\
    ${SRC_URI_AFBUS} \
    ${SRC_URI_COMMONAPI} \
"

EXTRA_OECONF = "--enable-developer \
    --enable-verbose-mode \
    --enable-asserts \
    "
