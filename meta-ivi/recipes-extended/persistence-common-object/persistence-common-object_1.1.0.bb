SUMMARY = "Common persistence object used by client library and administrator"
HOMEPAGE = "https://at.projects.genivi.org/wiki/display/PROJ/GENIVI+Persistence+Management"
BUGTRACKER = "https://at.projects.genivi.org/jira/browse/PCL"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r2"

SRCREV = "6e827a990f7e0dceeaeddb7f16c710b7cc30f2fe"
SRC_URI = " git://github.com/GENIVI/${BPN}.git;protocol=https \
    "
S = "${WORKDIR}/git"

PV = "1.0.3+git${SRCPV}"

DEPENDS = "glib-2.0 glib-2.0-native dlt-daemon libcheck libarchive"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = " --with-database=key-value-store "

do_install_append() {
    rm -rf ${D}${sysconfdir}
}

FILES_${PN} := "${prefix}"
