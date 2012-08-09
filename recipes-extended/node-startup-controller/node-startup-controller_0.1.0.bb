SUMMARY = "GENIVI Node Startup Controller"
DESCRIPTION = "The GENIVI Node Startup Controller \
		- manages the Last User Context (LUC) \
		- monitors target startup \
		  and \
		- manages legacy applications within a node \
		"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://git.genivi.org/srv/git/node-startup-controller;protocol=git;tag=85a9ee7e9b93d9dc7d16fbff3c3f35a5640efdea"

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
