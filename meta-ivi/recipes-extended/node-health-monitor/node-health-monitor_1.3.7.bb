SUMMARY = "Genivi NodeHealthMonitor"
DESCRIPTION = "Monitor to observe system health."
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r3"

SRCREV = "80ae9bb60a40d1fc3a43f81119030e35395c7a8c"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https \
    file://fix-no-libsystemd-daemon.patch \
    file://0001-change-service-name.patch \
    file://0001-node-health-monitor-set-valid-format-message-for-g_s.patch \
    "
S = "${WORKDIR}/git"

DEPENDS = "dlt-daemon glib-2.0 glib-2.0-native \
    node-state-manager persistence-client-library"

inherit pkgconfig autotools-brokensep

FILES_${PN} += "\
    ${datadir}/dbus-1/* \
    ${systemd_unitdir}/system/* \
    "
