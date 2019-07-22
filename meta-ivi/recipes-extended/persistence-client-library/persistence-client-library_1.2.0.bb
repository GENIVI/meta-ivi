SUMMARY = "GENIVI Persistence Client Library"
DESCRIPTION = "The Persistence Management is responsible to handle \
    persistent data, including all data read and modified often during \
    a lifetime of an infotainment system."
HOMEPAGE = "https://at.projects.genivi.org/wiki/display/PROJ/Persistence+Client+Library"
BUGTRACKER = "https://at.projects.genivi.org/jira/browse/PCL"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823"

DEPENDS = "dlt-daemon dbus libcheck persistence-common-object"

SRCREV = "797e31444eff1ec4d9977ed040696b2baff09752"
SRC_URI = " \
    git://github.com/GENIVI/${BPN}.git;protocol=https \
    file://0001-fix-exec-path.patch \
    file://0002-load-correct-version-of-libpers_common.patch \
    "
S = "${WORKDIR}/git"

PV = "1.1.0+git${SRCPV}"
PR = "r5"

inherit pkgconfig autotools-brokensep

EXTRA_OECONF_append = " --enable-tools --enable-pasinterface"

PARALLEL_MAKE = ""

# .so files are the actual libraries
FILES_SOLIBSDEV = ""

FILES_${PN} += " \
    ${libdir}/lib*custom.so \
    ${libdir}/*.so.* \
    ${sysconfdir} \
    "
FILES_${PN}-dev += " \
    ${libdir}/libpersistence_client_library.so \
    "

RDEPENDS_${PN} = " \
    node-state-manager \
    persistence-administrator \
"
