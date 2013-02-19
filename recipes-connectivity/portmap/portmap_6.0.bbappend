PRINC := "${@int(PRINC) + 5}"

# Find local ${PN} directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

inherit systemd

SYSTEMD_SERVICE = "portmap.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} =+ "${systemd_unitdir}/system/portmap.service"

SRC_URI_append = " \
    file://portmap.service \
    "

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/portmap.service ${D}${systemd_unitdir}/system
    fi
}
