FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# add support for GENIVI CommonAPI D-Bus runtime
# - http://projects.genivi.org/commonapi/
SRC_URI_append = " \
    file://capi-dbus-add-send-with-reply-set-notify.patch \
    file://capi-dbus-add-support-for-custom-marshalling.patch \
    file://capi-dbus-block-acquire-io-path-on-send.patch \
    file://capi-dbus-correct-dbus-connection-block-pending-call.patch \
    file://build_fixup.patch \
    "
