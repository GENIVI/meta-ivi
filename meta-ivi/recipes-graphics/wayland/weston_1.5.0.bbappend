FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

#
# Add weston-ivi-shell patches
# Repo: https://github.com/Tarnyko/weston-ivi-shell
# Branch: genivi-1.2.0
#
SRC_URI_append = " \
    file://0001-README-modifed-for-ivi-shell.patch \
    file://0002-protocol-add-interface-ivi_application-extension.patch \
    file://0003-ivi-shell-add-the-shell-plugin-for-In-Vehicle-Infota.patch \
    file://0004-ivi-shell-add-IVI-layout-APIs.patch \
    file://0005-protocol-ivi-hmi-controller-protocol-to-set-up-IVI-s.patch \
    file://0006-ivi-shell-a-reference-implementation-how-to-use-ivi-.patch \
    file://0007-clients-a-reference-implementation-of-UI-client-how-.patch \
    file://0008-data-add-reference-image-files-for-weston-ivi-shell-.patch \
    file://0009-ivi-shell-a-reference-of-weston.ini-for-ivi-shell-an.patch \
    file://0010-clients-modify-example-clients-to-support-ivi-applic.patch \
    file://0011-ingnore-data-wayland.png.patch \
    file://0012-Reference-implementation-of-input-panel-for-ivi-shel.patch \
    file://0013-If-window-is-requsted-by-Virtual-keyboard-ivi_applic.patch \
    file://0014-input-panel-Change-unsigned-to-signed-to-follow-west.patch \
    file://0015-This-applies-transition-animation-to-ivi-shell.patch \
    file://0016-Update-README-to-show-how-to-build-congirue-and-star.patch \
    file://0017-ivi-shell-Fix-incorrect-member-reference-in-wl_list_.patch \
    file://0018-ivi-shell-Set-source-surface-size-if-surfaceConfigur.patch \
    file://0019-ivi-shell-Add-IVI-layout-APIs.patch \
    file://0020-ivi-layout-fix-transform-matrix-could-not-be-inverti.patch \
    file://0021-To-get-resolution-of-a-screen-refer-width-and-height.patch \
    file://0022-input.c-give-control-of-default_grab_keyboard_key-to.patch \
    file://0023-ivi-layout-send-key-event-to-clients-that-are-bindin.patch \
    file://0024-DEBUG-add-weston-simple-keyboard-binding.patch \
    file://0025-ivi-application.xml-Add-configure-event-in-ivi_surfa.patch \
    file://0026-ivi-layout-Add-configured-signal-in-ivi_layout_surfa.patch \
    file://0027-ivi-shell-send-ivi_surface-configure-event-when-ivi_.patch \
    file://0028-ivi-layout-fix-calculation-of-view-scale.patch \
    file://0029-DEBUG-simple-egl-receive-configure-event-and-resize-.patch \
    file://0030-simple-shm-support-ivi_surface-configure-event.patch \
    file://0031-window.c-support-ivi_surface-configure-event.patch \
    "

#
# [PATCH] Add the processing which has notice of click event from compositor
#   Signed-off-by: Nobuhiko Tanibata <NOBUHIKO_TANIBATA at xddp.denso.co.jp>
SRC_URI_append = "\
    file://ivi-shell-click-event.patch \
    "
#
# [PATCH] Enable/disable default virtual keyboard
# Signed-off-by: Imran Zaman <imran.zaman@intel.com>
SRC_URI_append = "\
    file://0001-Enable-disable-default-virtual-keyboard.patch \
    "

EXTRA_OECONF += "\
    --disable-default-vkb \
    "

#
# TO DO
# Remove when bug is fixed: https://bugzilla.yoctoproject.org/show_bug.cgi?id=5968
# Due to a yocto bug, applying patches with something else then quilt fails.
# We can't use quilt for this (0008) patch as this tool does not support binary
# diffs. So we manually copy files from:
# 0008-data-add-reference-image-files-for-weston-ivi-shell-.patch
# The above patch was modified to not apply binary diffs but only text diff.
#
SRC_URI_append = " \
    file://background.png \
    file://home.png \
    file://icon_ivi_flower.png \
    file://icon_ivi_simple-shm.png \
    file://panel.png \
    file://sidebyside.png \
    file://fullscreen.png \
    file://icon_ivi_clickdot.png \
    file://icon_ivi_simple-egl.png \
    file://icon_ivi_smoke.png \
    file://random.png \
    file://tiling.png \
    "

FILES_${PN} += "${libdir}/weston/* ${sysconfdir}/xdg"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"

do_configure_append() {
    # TO DO
    # Remove when bug is fixed: https://bugzilla.yoctoproject.org/show_bug.cgi?id=5968
    # Copy files from 0007-data-add-reference-image-files-for-weston-ivi-shell-.patch
    cp -rvf ${WORKDIR}/*.png ${S}/data
}

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
