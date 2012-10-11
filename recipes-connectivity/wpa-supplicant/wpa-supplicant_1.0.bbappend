PRINC = "1"

# Find "files" directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI_append = " file://wpa_supplicant.service"

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${libdir}/systemd/system/"

do_install_append() {
        mkdir -p ${D}/${libdir}/systemd/system
        install -m 644 ${WORKDIR}/wpa_supplicant.service ${D}${libdir}/systemd/system
}

