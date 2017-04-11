SUMMARY = "GENIVI Persistence Client Library"
DESCRIPTION = "The Persistence Management is responsible to handle \
    persistent data, including all data read and modified often during \
    a lifetime of an infotainment system."
HOMEPAGE = "http://projects.genivi.org/persistence-client-library"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Persistence"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823"

DEPENDS = "dlt-daemon dbus libcheck persistence-common-object"

SRCREV = "03fa5d83d582cb54acb4dc00a11715523d936fbf"
SRC_URI = " \
    git://github.com/GENIVI/persistence-client-library.git;protocol=https \
    "
S = "${WORKDIR}/git"

inherit pkgconfig autotools-brokensep

PARALLEL_MAKE = ""

# .so files are the actual libraries
FILES_SOLIBSDEV = ""
#SOLIBS = "${SOLIBSDEV}"

FILES_${PN} += " \
    ${libdir}/lib*custom.so \
    ${libdir}/*.so.* \
    ${sysconfdir} \
    "
FILES_${PN}-dev += " \
    ${libdir}/libpersistence_client_library.so \
    "

do_install_append() {
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 0644 ${S}/config/org.genivi.persistence.admin.conf ${D}${sysconfdir}/dbus-1/system.d
}

RDEPENDS_${PN} = "node-state-manager"
