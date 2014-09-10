FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "X.service"

SRC_URI_append = " file://X.service \
                 "
do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/X.service ${D}${systemd_unitdir}/system
    fi
}
