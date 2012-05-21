PRINC = "1"

# Find "files" directory
FILESEXTRAPATHS := "${THISDIR}/files"

SRC_URI_append = " file://portmap.service"

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "/lib/systemd/system/"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

fakeroot do_install() {
	#install -d ${D}${mandir}/man8/ ${D}${base_sbindir} ${D}${sysconfdir}/init.d
	install -d ${D}${mandir}/man8/ ${D}${base_sbindir}
	#install -m 0755 ${WORKDIR}/portmap.init ${D}${sysconfdir}/init.d/portmap
	oe_runmake install DESTDIR=${D}
}

do_install_append() {
        mkdir -p ${D}/lib/systemd/system
        install -m 644 ${WORKDIR}/portmap.service ${D}/lib/systemd/system/
}

