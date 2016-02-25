SUMMARY = "GENIVI Persistence Client Library"
DESCRIPTION = "The Persistence Management is responsible to handle \
    persistent data, including all data read and modified often during \
    a lifetime of an infotainment system."
HOMEPAGE = "http://projects.genivi.org/persistence-client-library"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Persistence"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823"

DEPENDS = "dlt-daemon dbus libcheck persistence-common-object"

SRC_URI = " \
    git://git.projects.genivi.org/persistence/persistence-client-library.git;tag=v${PV} \
    file://0001-load-correct-version-of-libpers_common.patch \
    file://0001-fix-exec-path.patch \
    "
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

#
# for test
#
EXTRA_OECONF += "--enable-tests"

do_install_append() {
   install -d ${D}${bindir}
   install -m 0755 ${S}/test/.libs/persistence_client_library_test ${D}${bindir}
   #install -m 0755 ${S}/test/.libs/persistence_pfs_test           ${D}${bindir}
   #install -m 0755 ${S}/test/pers_pfs_test_starter.sh             ${D}${bindir}
   install -d ${D}/Data
   install -m 0644 ${S}/test/data/PAS_data.tar.gz          ${D}/Data/Data.tar.gz
   #install -d ${D}/lib/systemd/system/
   #install -m 0755 ${S}/test/persistence-pfs-test-start.service ${D}/lib/systemd/system/
}

PACKAGES += "${PN}-testenv"
RDEPENDS_${PN} += "${PN}-testenv"

#inherit systemd
#
#SYSTEMD_PACKAGES = "${PN}-testenv"
#SYSTEMD_SERVICE_${PN}-testenv = "persistence-pfs-test-start.service"
#SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN}-testenv = " \
   ${bindir}/persistence_client_library_test \
   /Data/Data.tar.gz \
"
#   ${bindir}/persistence_pfs_test \
#   ${bindir}/pers_pfs_test_starter.sh \
#
