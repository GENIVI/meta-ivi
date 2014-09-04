SUMMARY = "Administrator application for persistence"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://git.projects.genivi.org/persistence/persistence-administrator.git;protocol=http \
          file://libsystemd_daemon.patch \
          "

SRCREV = "3a6cc3228b93c647f0ffed4b53641572e78f71d7"

DEPENDS = "glib-2.0 dbus dlt-daemon libarchive zlib json-c node-state-manager \
           systemd persistence-common-object libffi libitzam"

S = "${WORKDIR}/git"

inherit autotools-brokensep systemd pkgconfig

FILES_${PN}-dev += "${datadir}/dbus-1/"

SYSTEMD_SERVICE = "pas-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"
