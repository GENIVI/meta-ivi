FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

#
# Add weston-ivi-shell patches
# Repo: https://github.com/Tarnyko/weston-ivi-shell
# Branch: weston150_ivi-shell_v6
#
SRC_URI_append = " \
    file://0001-protocol-add-interface-ivi_application-extension.patch \
    file://0002-ivi-shell-add-the-shell-plugin-for-In-Vehicle-Infota.patch \
    file://0003-ivi-shell-add-IVI-layout-APIs.patch \
    file://0004-protocol-ivi-hmi-controller-protocol-to-set-up-IVI-s.patch \
    file://0005-ivi-shell-a-reference-implementation-how-to-use-ivi-.patch \
    file://0006-clients-a-reference-implementation-of-UI-client-how-.patch \
    file://0008-ivi-shell-a-reference-of-weston.ini-for-ivi-shell-an.patch \
    file://0009-clients-modify-example-clients-to-support-ivi-applic.patch \
    file://0010-Add-a-README.md-for-GitHub-with-screenshots-and-inst.patch \
    file://0011-README.md-fix-typo-it-is-weston.ini.patch \
    file://0012-README.md-add-video.patch \
    file://0013-README.md-add-better-video-from-upstream-developer-b.patch \
    file://0014-README.md-fix-spacing-between-video-URLs.patch \
    file://0015-Add-video-3-captured-on-Freescase-imx6-dual.patch \
    "

#
# TO DO
# Remove when bug is fixed: https://bugzilla.yoctoproject.org/show_bug.cgi?id=5968
# Due to a yocto bug, applying patches with something else then quilt fails.
# We can't use quilt for this (0007) patch as this tool does not support binary
# diffs. So we manually copy files from:
# 0007-data-add-reference-image-files-for-weston-ivi-shell-.patch
#
SRC_URI_append = " \
    file://background.png \
    file://home.png \
    file://icon_ivi_flower.png \
    file://icon_ivi_simple-shm.png \
    file://panel.png \
    file://pattern.png \
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

do_install_append() {
    # TO DO
    # Remove when bug is fixed: https://bugzilla.yoctoproject.org/show_bug.cgi?id=5968
    # Copy files from 0007-data-add-reference-image-files-for-weston-ivi-shell-.patch
    cp -rvf ${WORKDIR}/*.png ${D}/${datadir}/weston

    WESTON_INI_CONFIG=${sysconfdir}/xdg/weston
    install -d ${D}${WESTON_INI_CONFIG}
    install -m 0644 ${S}/ivi-shell/weston.ini.in ${D}${WESTON_INI_CONFIG}/weston.ini
    sed -i -e 's/hmi-controller.so/hmi-controller.so,ivi-controller.so/' \
          -e 's|\@libexecdir\@|${libexecdir}|' \
          -e 's|\@abs_top_builddir\@\/data|${datadir}\/weston|' \
          -e 's|\@abs_top_builddir\@\/clients|${bindir}|' \
          -e 's|\@abs_top_builddir\@\/weston-ivi-shell-user-interface|${libdir}/weston/weston-ivi-shell-user-interface|' ${D}${WESTON_INI_CONFIG}/weston.ini

}
