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

inherit gzipnative

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=99ba60c3fad7eaf8c56bca6dd75cba09 \
		    file://MPL.txt;md5=ccdb2761cef70c8b2612624c323f89dc"

SRC_URI = "git://git.genivi.org/srv/git/DLT-daemon;protocol=git;tag=e08037988036d3a66dbd592576e59c8f834c61c4"

DEPENDS = ""

S = "${WORKDIR}/git"

inherit autotools gettext cmake

FILES_${PN}-doc += "/usr/share/*"
