PR = "r1"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}:${THISDIR}/${PN}-${PV}"

# We patch compositor.c so ignore license in that file
LIC_FILES_CHKSUM = "file://COPYING;md5=275efac2559a224527bd4fd593d38466"

# Weston ivi shell needs libinput backend
PACKAGECONFIG_append += "libinput"

# Use git repository because we want to use 'git' for applying patches
SRC_URI_remove = "http://wayland.freedesktop.org/releases/${BPN}-${PV}.tar.xz"
SRC_URI_append = "git://anongit.freedesktop.org/wayland/${PN}"
SRCREV = "2858cc2a50854685b44eee72994dea57f5aa4246"
S = "${WORKDIR}/git"

#
# Add weston-ivi-shell patches
# Repo: https://github.com/ntanibata/weston-ivi-shell
# Branch: weston-ivi-shell-1.6.0-WIP-06-working
#
SRC_URI_append = " \
    file://0001-README-modifed-for-ivi-shell.patch \
    file://0002-protocol-add-interface-ivi_application-extension.patch \
    file://0003-ivi-shell-add-IVI-layout-APIs.patch \
    file://0004-ivi-shell-add-the-shell-plugin-for-In-Vehicle-Infota.patch \
    file://0005-protocol-ivi-hmi-controller-protocol-to-set-up-IVI-s.patch \
    file://0006-ivi-shell-a-reference-implementation-how-to-use-ivi-.patch \
    file://0007-clients-a-reference-implementation-of-UI-client-how-.patch \
    file://0008-data-add-reference-image-files-for-weston-ivi-shell-.patch \
    file://0009-ivi-shell-a-reference-of-weston.ini-for-ivi-shell-an.patch \
    file://0010-clients-support-ivi-application.xml-for-clients-simp.patch \
    file://0011-clients-support-ivi-application.xml-for-clients-simp.patch \
    file://0012-clients-support-ivi-application.xml-for-clients-wind.patch \
    file://0013-Reference-implementation-of-input-panel-for-ivi-shel.patch \
    file://0014-input-panel-ivi-add-seat-keyboard-focus-to-condition.patch \
    file://0015-protocol-ivi-application.xml-update-description-of-i.patch \
    "

# We use git because we need binary diff support or the above patches (png files)
PATCHTOOL = 'git'

# or use this.
#SRCREV = "814f152d3f3ca5973162cad8c19f1e86f85f6d27"
#SRC_URI_append += "git://github.com/ntanibata/weston-ivi-shell.git;protocol=https;branch=weston-ivi-shell-1.6.0-WIP-06-working"

# [PATCH] Add the processing which has notice of click event from compositor
# Signed-off-by: Nobuhiko Tanibata <NOBUHIKO_TANIBATA at xddp.denso.co.jp>
SRC_URI_append = " file://ivi-shell-click-event.patch"

# [PATCH] Enable/disable default virtual keyboard
# Signed-off-by: Imran Zaman <imran.zaman@intel.com>
SRC_URI_append = " file://Enable-disable-default-virtual-keyboard.patch"
EXTRA_OECONF += "--disable-default-vkb"

# [PATCH] fix up for GENIVI baseline 9.0
# Signed-off-by: Yong-iL Joh <yong-il.joh@windriver.com>
SRC_URI_append = " file://build.fix.up.patch"

FILES_${PN} += "${libdir}/weston/* ${sysconfdir}/xdg"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"

do_install_append() {
    WESTON_INI_CONFIG=${sysconfdir}/xdg/weston
    install -d ${D}${WESTON_INI_CONFIG}
    install -m 0644 ${S}/ivi-shell/weston.ini.in ${D}${WESTON_INI_CONFIG}/weston.ini
    sed -i -e 's/hmi-controller.so/hmi-controller.so,ivi-controller.so/' \
          -e 's|\@libexecdir\@|${libexecdir}|' \
          -e 's|\@abs_top_builddir\@\/data|${datadir}\/weston|' \
          -e 's|\@abs_top_builddir\@\/clients|${bindir}|' \
          -e 's|\@abs_top_builddir\@\/weston-ivi-shell-user-interface|${libdir}/weston/weston-ivi-shell-user-interface|' ${D}${WESTON_INI_CONFIG}/weston.ini

}
