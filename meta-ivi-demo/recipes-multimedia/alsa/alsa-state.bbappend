FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = " file://asound.conf"

do_install_append() {
    cp ${WORKDIR}/asound.conf ${D}/etc
}
