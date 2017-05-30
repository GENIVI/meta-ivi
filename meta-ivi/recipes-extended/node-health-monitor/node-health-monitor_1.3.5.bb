SUMMARY = "Genivi NodeHealthMonitor"
DESCRIPTION = "Monitor to observe system health."
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r3"

SRCREV="6aa24c04080c3cd0389934841fae5ac502b8e13a"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https \
           file://fix-no-libsystemd-daemon.patch \
           file://0001-change-service-name.patch \
          "
S = "${WORKDIR}/git"

DEPENDS = "dlt-daemon glib-2.0 glib-2.0-native \
    node-state-manager persistence-client-library"

inherit pkgconfig autotools-brokensep

FILES_${PN} += "\
    ${datadir}/dbus-1/* \
    ${systemd_unitdir}/system/* \
    "
