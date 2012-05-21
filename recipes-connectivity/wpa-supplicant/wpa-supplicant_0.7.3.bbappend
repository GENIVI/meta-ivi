PRINC = "1"

# Find "files" directory
FILESEXTRAPATHS := "${THISDIR}/files"

SRC_URI_append_vexpressa9 = " file://wpa_supplicant.service"

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "/lib/systemd/system/"

do_install_append() {

        mkdir -p ${D}/lib/systemd/system
        install -m 644 ${WORKDIR}/wpa_supplicant.service ${D}/lib/systemd/system

}

