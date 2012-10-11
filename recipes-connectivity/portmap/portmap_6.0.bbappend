PRINC = "2"

# Find local "files" and "${PN}" directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI_append = " file://portmap.service \
                 "

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${libdir}/systemd/system/"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

do_install_append() {
        mkdir -p ${D}/${libdir}/systemd/system
        install -m 644 ${WORKDIR}/portmap.service ${D}/${libdir}/systemd/system/
}
