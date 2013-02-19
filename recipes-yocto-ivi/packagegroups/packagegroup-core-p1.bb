DESCRIPTION = "P1 packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"

inherit packagegroup

PACKAGES = "\
    packagegroup-core-p1 \
    "

ALLOW_EMPTY = "1"

RDEPENDS_packagegroup-core-p1 = "\
    kernel-modules \
    audiomanager \
    audiomanager-systemd \
    dlt-daemon \
    dlt-daemon-systemd \
    alsa-lib  \
    alsa-utils \
    bluez4 \
    bluez4-systemd \
    connman \
    curl \
    dbus \
    ecryptfs-utils \
    eglibc \
    fuse \
    gstreamer \
    gst-plugins-good \
    gst-plugins-base \
    layer-management \
    libusb1 \
    nfs-utils \
    openssl \
    quota \
    sqlite3 \
    udev \
    wpa-supplicant \
    "

RRECOMMENDS_${PN} = "\
    packagegroup-xserver-ivi \
    "
