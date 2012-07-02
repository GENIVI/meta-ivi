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
    dhcp-client \
    dhcp-client-systemd \
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
    ntp \
    ntp-systemd \
    openssl \
    portmap-systemd \
    qt4-embedded \
    quota \
    sqlite3 \
    udev \
    usbutils \
    wpa-supplicant \
    wpa-supplicant-systemd \
    "

RRECOMMENDS_${PN} = "\
    task-xserver-ivi \
    "
