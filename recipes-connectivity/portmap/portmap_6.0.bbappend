PRINC = "1"

# Find local "files" and "${PN}" directory
FILESEXTRAPATHS := "${THISDIR}/files:${THISDIR}/${PN}"

SRC_URI_append = " file://portmap.service \
                   file://define-sbindir.patch \
                 "

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${libdir}/systemd/system/"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

sbindir = "/usr/sbin"

fakeroot do_install() {
	#install -d ${D}${mandir}/man8/ ${D}${base_sbindir} ${D}${sysconfdir}/init.d
	install -d ${D}${mandir}/man8/ ${D}${sbindir}
	#install -m 0755 ${WORKDIR}/portmap.init ${D}${sysconfdir}/init.d/portmap
	oe_runmake install DESTDIR=${D} SBINDIR=${sbindir}
}

do_install_append() {
        mkdir -p ${D}/${libdir}/systemd/system
        install -m 644 ${WORKDIR}/portmap.service ${D}/${libdir}/systemd/system/
}

FILES_${PN}-utils = "${sbindir}/pmap_set \
                     ${sbindir}/pmap_dump"