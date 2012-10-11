PRINC = "1"

FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI_append_vexpressa9 = " file://securetty-vea9"

do_configure_prepend_vexpressa9 () {
    cat ${WORKDIR}/securetty-vea9 >> ${WORKDIR}/securetty
}
