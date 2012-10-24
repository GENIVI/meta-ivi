SUMMARY = "Diagnostic Log and Trace"
DESCRIPTION = "This component provides a standardised log and trace interface, based on \
		thestandardised protocol specified in the AUTOSAR standard 4.0 DLT.This \
		component can be used by GENIVI components and other applications aslogging \
		facility providing \
		- the DLT shared library \
		- the DLT daemon, including startup scripts \
		- the DLT daemon adaptors- the DLT client console utilities \
		- the DLT test applications \
		"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "console/utils"

PR = "r3"

inherit gzipnative

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=99ba60c3fad7eaf8c56bca6dd75cba09 \
		    file://MPL.txt;md5=ccdb2761cef70c8b2612624c323f89dc"

SRC_URI = "git://git.projects.genivi.org/${PN}.git;protocol=git;tag=82b1b748b207fe85db97089be5f78a5e9ab551d1"

S = "${WORKDIR}/git"

inherit autotools gettext cmake

FILES_${PN}-doc += "/usr/share/*"
FILES_${PN}-systemd += "${systemd_unitdir}/system/"

PACKAGES =+ "${PN}-systemd"

EXTRA_OECMAKE = "-DWITH_SYSTEMD=ON"
