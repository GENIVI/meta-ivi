FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://weston.ini.in"

do_install_append() {
    WESTON_INI_CONFIG=${sysconfdir}/xdg/weston
    install -d ${D}${WESTON_INI_CONFIG}
    install -m 0644 ${WORKDIR}/weston.ini.in ${D}${WESTON_INI_CONFIG}/weston.ini
    sed -i -e 's/hmi-controller.so/ivi-controller.so\nivi-input-module=ivi-input-controller.so/' \
          -e 's|\@westondatadir\@|${datadir}\/weston|' \
          -e 's|\@bindir\@|${bindir}|' \
          ${D}${WESTON_INI_CONFIG}/weston.ini
}
