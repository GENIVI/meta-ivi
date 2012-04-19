DESCRIPTION = "P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

PACKAGES = "\
    task-core-p1 \
    task-core-p1-dbg \
    task-core-p1-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-core-p1 = "\
    DLT-daemon \
    alsa-lib  \
    alsa-utils \
    bluez4 \
    connman \
    curl \
    dbus \
    dhcp-client \
    ecryptfs-utils \
    eglibc \
    fuse \
    gstreamer \
    gst-plugins-good \
    gst-plugins-base \
    layer-management \
    libusb1 \
    nfs-utils \
    ntp \
    openssl \
    qt4-embedded \
    quota \
    sqlite3 \
    udev \
    usbutils \
    wpa-supplicant \
    "

RRECOMMENDS_${PN} = "\
    task-xserver-ivi \
    "
