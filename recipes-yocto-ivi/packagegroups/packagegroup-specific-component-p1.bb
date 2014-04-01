DESCRIPTION = "GENIVI P1 Specific components (SC)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
# Avoid hardcoding the full layer path into the checksums
LIC_FILES_CHKSUM[vardepsexclude] += "IVI_COREBASE"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    packagegroup-specific-component-p1 \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    kernel-modules \
    audiomanager \
    common-api-c++ \
    dlt-daemon \
    dlt-daemon-systemd \
    alsa-lib  \
    alsa-utils \
    connman \
    curl \
    dbus \
    ecryptfs-utils \
    gstreamer \
    gst-plugins-good \
    gst-plugins-base \
    libusb1 \
    node-startup-controller \
    node-state-manager \
    openssl \
    quota \
    sqlite3 \
    udev \
    wpa-supplicant \
    "
