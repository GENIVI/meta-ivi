FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = " file://client_conf.patch \
                   file://daemon_conf.patch \
                   file://am_poc.pa \
"

do_install_append() {
    cp ${WORKDIR}/am_poc.pa ${D}/etc/pulse
}
