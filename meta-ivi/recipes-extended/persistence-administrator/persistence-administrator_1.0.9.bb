SUMMARY = "Administrator application for persistence"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r0"

SRCREV = "635b4843b5189d68c38abfa14c9cd61815b2152b"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 glib-2.0-native dbus dlt-daemon libarchive zlib json-c \
           node-state-manager systemd persistence-common-object libffi"

inherit autotools-brokensep systemd pkgconfig

FILES_${PN}-dev += "${datadir}/dbus-1/"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "pas-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_configure_prepend() {
   cp ${S}/README.md ${S}/README
}

do_install_append() {
   perl -pi -e 's/dbus-public-bus.service/dbus.service/' \
	${D}/lib/systemd/system/pas-daemon.service
   rm -f ${D}${bindir}/pers_admin_test_framework
}
