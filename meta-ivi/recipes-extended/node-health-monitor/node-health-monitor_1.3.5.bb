SUMMARY = "Genivi NodeHealthMonitor"
DESCRIPTION = "Monitor to observe system health."
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r2"

SRC_URI = "git://git.projects.genivi.org/lifecycle/${PN}.git;tag=${PV} \
           file://fix-no-libsystemd-daemon.patch \
          "
S = "${WORKDIR}/git"

DEPENDS = "dlt-daemon glib-2.0 node-state-manager persistence-client-library"

inherit pkgconfig autotools-brokensep

FILES_${PN} += "\
    ${datadir}/dbus-1/* \
    ${systemd_unitdir}/system/* \
    "
