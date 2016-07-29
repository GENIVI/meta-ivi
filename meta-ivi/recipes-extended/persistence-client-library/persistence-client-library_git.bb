SUMMARY = "GENIVI Persistence Client Library"
DESCRIPTION = "The Persistence Management is responsible to handle \
persistent data, including all data read and modified often during \
a lifetime of an infotainment system."
HOMEPAGE = "http://projects.genivi.org/persistence-client-library"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Persistence"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823"

DEPENDS = "dlt-daemon dbus libcheck persistence-common-object"

PV = "7.0+git${SRCPV}"

SRC_URI = "git://git.projects.genivi.org/persistence/persistence-client-library.git;protocol=http"
SRCREV  = "441b3ce710ec6b7f1e6360cdf29e5c4243af9f1e"
S = "${WORKDIR}/git"

inherit pkgconfig autotools-brokensep

PARALLEL_MAKE = ""

FILES_${PN} = " \
   ${libdir}/*.so \
   ${libdir}/*.so.* \
   ${sysconfdir} \
"

do_install_append() {
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 0644 ${S}/config/org.genivi.persistence.admin.conf ${D}${sysconfdir}/dbus-1/system.d
}

RDEPENDS_${PN} = "node-state-manager"

