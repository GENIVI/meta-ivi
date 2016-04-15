SUMARY = "Common persistence object used by client library and administrator"
HOMEPAGE = "http://projects.genivi.org/persistence-client-library"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Persistence"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "b27fe577fcef7bf57d8d287f56e8dfd6ac60b1f0"
SRC_URI = " \
    git://git.projects.genivi.org/persistence/persistence-common-object.git \
    file://configure.ac-fix-typo.patch \
    file://B251_typedef_uint64_t.patch \
    "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 libitzam dlt-daemon"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = " \
    --with-dbuspolicydir=${sysconfdir} \
    --with-database=key-value-store \
    "

FILES_${PN} += "${datadir}"
