SUMMARY = "Diagnostic Log and Trace"
DESCRIPTION = "This component provides a standardised log and trace interface, \
based on the standardised protocol specified in the AUTOSAR standard 4.0 DLT. \
This component can be used by GENIVI components and other applications as \
logging facility providing: \
- the DLT shared library \
- the DLT daemon, including startup scripts \
- the DLT daemon adaptors- the DLT client console utilities \
- the DLT test applications"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "console/utils"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8184208060df880fe3137b93eb88aeea"

PR = "r2"

DEPENDS = "dbus zlib gzip-native"

SRCREV = "bdde2374efd567f27d7a4ec70911b34cda061d27"
SRC_URI = "git://github.com/GENIVI/${BPN}.git;protocol=https \
    file://0002-Don-t-execute-processes-as-a-specific-user.patch \
    file://0004-Modify-systemd-config-directory.patch \
    file://0001-Fix-compilation-with-glibc-2.28.patch \
    "
S = "${WORKDIR}/git"

inherit autotools gettext cmake systemd

PACKAGES += "${PN}-systemd"
SYSTEMD_PACKAGES = "${PN} ${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "dlt-system.service dlt.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"
SYSTEMD_SERVICE_${PN}-systemd = "dlt-example-user.service \
    dlt-dbus.service \
    dlt-adaptor-udp.service \
    dlt-receive.service"
SYSTEMD_AUTO_ENABLE_${PN}-systemd = "disable"

EXTRA_OECMAKE = "-DWITH_SYSTEMD=ON"

FILES_${PN}-doc += "/usr/share/dlt-filetransfer"

do_install_append() {
   rm -f ${D}${bindir}/dlt-test-*
}
