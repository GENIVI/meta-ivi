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
SECTION = ""console/utils"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://git.genivi.org/srv/git/boot-manager;protocol=git;tag=85a9ee7e9b93d9dc7d16fbff3c3f35a5640efdea"

DEPENDS = ""

S = "${WORKDIR}/git"

inherit autotools

FILES_${PN} += "/usr/share/* \
                ${libdir}/boot-manager-1/boot-manager \
                ${libdir}/legacy-app-handler-1/legacy-app-handler \
                ${libdir}/luc-handler-1/luc-handler \
               "
FILES_${PN}-dbg += "${libdir}/luc-handler-1/.debug/ \
                    ${libdir}/legacy-app-handler-1/.debug/ \
                    ${libdir}/boot-manager-1/.debug/ \
                   "
