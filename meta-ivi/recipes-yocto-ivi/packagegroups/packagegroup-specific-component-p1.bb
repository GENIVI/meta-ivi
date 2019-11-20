DESCRIPTION = "GENIVI P1 Specific components (SC)"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-specific-component-p1 \
"

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    alsa-utils \
    audiomanager \
    common-api-c++ \
    connman \
    curl \
    dbus \
    dlt-daemon \
    gstreamer1.0-meta-base \
    kernel-modules \
    libasound  \
    libusb1 \
    node-startup-controller \
    node-state-manager \
    openssl \
    udev \
    wpa-supplicant \
"
