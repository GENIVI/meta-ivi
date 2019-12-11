SUMMARY = "Common persistence object used by client library and administrator"
HOMEPAGE = "https://at.projects.genivi.org/wiki/display/PROJ/GENIVI+Persistence+Management"
BUGTRACKER = "https://at.projects.genivi.org/jira/browse/PCL"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r2"

SRCREV = "ef393dc635875bb0bbb25abf66cfe42cfa5d6842"
SRC_URI = " git://github.com/GENIVI/${BPN}.git;protocol=https \
    "
S = "${WORKDIR}/git"

PV = "1.0.3+git${SRCPV}"

DEPENDS = "glib-2.0 glib-2.0-native dlt-daemon libcheck libarchive"

inherit autotools-brokensep pkgconfig

# For unknown reason the pkg-config fails (returns empty for the "datadir"
# definition for this component. That causes the default D-Bus policy dir
# to end up in root directory, and then not get packaged as expected.
# Setting dbuspolicydir like this is maybe not the cleanest solution but
# it's a # stopgap solution for this moment. (Feel free to send patches).
EXTRA_OECONF = " --with-database=key-value-store --with-dbuspolicydir=/usr/share/dbus-1/system.d "

FILES_${PN} := "${prefix}"
