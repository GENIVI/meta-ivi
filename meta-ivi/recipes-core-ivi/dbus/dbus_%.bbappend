FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# add support for GENIVI CommonAPI D-Bus runtime
# - https://genivi-oss.atlassian.net/wiki/display/COMMONAPICPP/CommonAPI-cpp
SRC_URI_append = " \
    file://capi-dbus-add-send-with-reply-set-notify.patch \
    file://0001-dbus-fix-possible-uninitialized-variable.patch \
    "
SRC_URI_append_qemux86 = " file://build_fix-ubunt1604-qemux86.patch"
