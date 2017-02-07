SUMARY = "Common persistence object used by client library and administrator"
HOMEPAGE = "http://projects.genivi.org/persistence-client-library"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Persistence"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "9f2fd043d034219a22b0ea84ee84ff9c07f05ec9"
SRC_URI = " \
    git://github.com/GENIVI/${BPN}.git;protocol=https \
    "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 dlt-daemon"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = " \
    --with-dbuspolicydir=${sysconfdir} \
    --with-database=key-value-store \
    "

FILES_${PN} += "${datadir}"
