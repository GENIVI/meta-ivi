SUMMARY = "Common persistence object used by client library and administrator"
HOMEPAGE = "https://at.projects.genivi.org/wiki/display/PROJ/GENIVI+Persistence+Management"
BUGTRACKER = "https://at.projects.genivi.org/jira/browse/PCL"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

SRCREV = "c1cd0d398ee0c25b5af6e4ff96b9991a1b24959d"
SRC_URI = " \
    git://github.com/GENIVI/persistence-common-object.git;protocol=https \
    "
S = "${WORKDIR}/git"

PV = "1.0.3+git${SRCPV}"

DEPENDS = "glib-2.0 glib-2.0-native dlt-daemon libcheck"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = " --with-database=key-value-store "

FILES_${PN} += "${datadir}"
