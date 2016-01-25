PR = "r1"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += "\
    file://xkb-data.tar.gz \
    "

FILES_${PN} += "/usr/share/X11/xkb/*"

do_install_append() {
    for i in keycodes rules symbols types compat keycodes; do
      install -d ${D}/usr/share/X11/xkb/$i
      install -m 0644 ${WORKDIR}/xkb-data/${i}/* \
              ${D}/usr/share/X11/xkb/$i
    done
}
