DESCRIPTION = "P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"

inherit packagegroup

PACKAGES = "\
    packagegroup-core-p1 \
    packagegroup-core-p1-dbg \
    packagegroup-core-p1-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_packagegroup-core-p1 = "\
    kernel-modules \
    AudioManager \
    AudioManager-systemd \
    DLT-daemon \
    DLT-daemon-systemd \
    alsa-lib  \
    alsa-utils \
    bluez4 \
    bluez4-systemd \
    connman \
    curl \
    dbus \
    ecryptfs-utils \
    ecryptfs-utils-systemd \
    eglibc \
    fuse \
    gstreamer \
    gst-plugins-good \
    gst-plugins-base \
    layer-management \
    libusb1 \
    nfs-utils \
    openssl \
    portmap-systemd \
    quota \
    sqlite3 \
    udev \
    wpa-supplicant \
    wpa-supplicant-systemd \
    "

RRECOMMENDS_${PN} = "\
    packagegroup-xserver-ivi \
    "
