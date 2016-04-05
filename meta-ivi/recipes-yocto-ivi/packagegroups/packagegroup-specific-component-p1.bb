DESCRIPTION = "GENIVI P1 Specific components (SC)"
LICENSE = "MIT"

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
    gstreamer1.0-meta-base \
    libusb1 \
    node-startup-controller \
    node-state-manager \
    openssl \
    udev \
    wpa-supplicant \
    "
