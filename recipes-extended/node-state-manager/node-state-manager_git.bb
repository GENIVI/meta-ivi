SUMMARY = "GENIVI Node State Manager"
DESCRIPTION = "The GENIVI Node State Manager \
		- is the central repository for information \
		- regarding the states/sessions \
		- inside the node. \
		"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://git.projects.genivi.org/lifecycle/node-state-manager.git;protocol=git;tag=4e48024924d9441cc4431b26bf6e1c9d1f3697e8"
PR = "r3"

EXTRA_OECONF = "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '', d)}"

DEPENDS = "glib-2.0 dlt-daemon"

S = "${WORKDIR}/git"

inherit autotools systemd

SYSTEMD_SERVICE = "nodestatemanager-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"


FILES_${PN} += "\
    ${datadir}/dbus-1/system-services/org.genivi.NodeStateManager.LifeCycleControl.service \
    ${systemd_unitdir}/system/nodestatemanager-daemon.service \
    "
