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
# Branch: weston-ivi-shell-1.6.0-WIP-14
#
SRC_URI_append = " \
    file://0001-configure.ac-bump-version-to-1.6.90.patch \
    file://0002-cosmetic-Remove-prototype-for-nonexistent-function.patch \
    file://0003-cosmetic-Move-WL_EXPORT-out-of-header-files.patch \
    file://0004-clients-Maximize-window-when-double-touch-on-title-b.patch \
    file://0005-shell-fix-weston_output_mode_switch-usage.patch \
    file://0006-protocol-add-presentation-extension-v5.patch \
    file://0007-compositor-add-stub-implementation-of-presentation-i.patch \
    file://0008-weston-info-report-presentation-clock.patch \
    file://0009-compositor-set-and-use-the-presentation-clock-everyw.patch \
    file://0010-compositor-implement-presentation_feedback.patch \
    file://0011-compositor-drm-deliver-frame-seq-for-feedback.patch \
    file://0012-tests-presentation-test-simple.patch \
    file://0013-clients-add-presentation-shm-demo.patch \
    file://0014-shell-don-t-crash-on-create_common_surface-failure.patch \
    file://0015-protocol-add-role-error-code-to-xdg_shell.patch \
    file://0016-compositor-send-error-for-surface-role-resets.patch \
    file://0017-tests-add-role-conflict-sub-surface-vs.-wl_shell_sur.patch \
    file://0019-Very-OCD-cosmetic-nitpick.patch \
    file://0020-gl-renderer-don-t-move-memory-in-output_rotate_damag.patch \
    file://0021-cosmetic-replace-boolean-function-return-values-with.patch \
    file://0022-data_device-change-version-while-initializing-data_d.patch \
    file://0023-window-compare-version-and-call-appropriate-destruct.patch \
    file://0024-compositor-Fix-weston_subsurface_is_synchronized-ret.patch \
    file://0025-compositor-Refactor-weston_output_mode_switch.patch \
    file://0026-input-allow-to-destroy-bindings-in-their-handler.patch \
    file://0027-compositor-check-version-before-calling-wl_output_se.patch \
    file://0028-compositor-stop-sending-spurious-events-on-mode-swit.patch \
    file://0029-compositor-make-keep_buffer-a-bool.patch \
    file://0030-input-fix-access-to-invalid-resource.patch \
    file://0031-smoke-Don-t-commit-an-xdg_surface-with-a-NULL-buffer.patch \
    file://0032-smoke-fix-valgrind-invalid-read-errors.patch \
    file://0033-man-Fix-some-weston.ini.man-typesetting-macros.patch \
    file://0034-compositor-Whitespace-only-Indent-with-tabs.patch \
    file://0035-compositor-Fix-tabbing-of-help-output.patch \
    file://0036-compositor-Fix-typos-in-help-text.patch \
    file://0037-compositor-Display-option-help-text-only-for-enabled.patch \
    file://0038-compositor-List-only-available-backends-in-help-text.patch \
    file://0039-compositor-add-missing-newline-to-help-text.patch \
    file://0040-input-don-t-send-to-clients-key-events-eaten-by-bind.patch \
    file://0041-input-use-tmp-var-in-pointer_unmap_sprite.patch \
    file://0042-xwm-find-a-seat-for-all-the-surface-s-views.patch \
    file://0043-compositor-Return-a-user-defined-exit-code.patch \
    file://0044-compositor-do-not-overwrite-error-exit-code.patch \
    file://0045-Revert-input-don-t-send-to-clients-key-events-eaten-.patch \
    file://0046-protocol-Fix-some-typos-in-protocol-files.patch \
    file://0047-toytoolkit-rename-tile_mask-to-render_shadow.patch \
    file://0048-cairo-util-fix-shadows-for-small-clients.patch \
    file://0049-backends-refactor-transform-string-parsing.patch \
    file://0050-gl-renderer-compress-pixman-bands-to-simplify-geomet.patch \
    file://0051-Makefile.am-Link-weston_multi_resource-against-rt-li.patch \
    file://0052-xwayland-fix-fullscreening.patch \
    file://0053-compositor-Add-idle-timeout-option-to-weston.ini.patch \
    file://0054-man-explain-idle-time-0-in-weston.ini.patch \
    file://0055-configure.ac-Indicate-headless-compositor-presence-i.patch \
    file://0056-compositor-headless-allow-rendering-with-pixman.patch \
    file://0057-compositor-headless-add-support-for-transforms-set-o.patch \
    file://0058-compositor-Document-options-for-headless-compositor.patch \
    file://0059-tests-Allow-tests-to-use-customized-command-line-par.patch \
    file://0060-input-don-t-run-the-key-bindings-on-focus-in.patch \
    file://0061-desktop-shell-don-t-crash-input-panel-if-no-kbd-focu.patch \
    file://0062-compositor-Drop-legacy-backends-in-favor-of-libinput.patch \
    file://0063-configure-remove-input-backend-result.patch \
    file://0064-simple-shm-explain-two-initial-roundtrips.patch \
    file://0065-input-Use-slightly-less-obscure-logic-in-evdev_notif.patch \
    file://0066-dnd-Fix-some-mouse-cursor-issues.patch \
    file://0067-tests-Include-config.h-as-first-header.patch \
    file://0068-tests-Cleanup-superfluous-headers.patch \
    file://0069-tests-Move-ARRAY_LENGTH-to-a-shared-header-for-reuse.patch \
    file://0070-tests-Use-one-shared-xzalloc-implementation.patch \
    file://0071-clients-Use-xmalloc-in-a-few-more-places.patch \
    file://0072-Use-zalloc-rather-than-malloc-and-manually-setting-m.patch \
    file://0073-Setting-members-to-0-NULL-after-a-zalloc-or-calloc-i.patch \
    file://0074-Use-zalloc-instead-of-calloc-1.patch \
    file://0075-rpi-renderer-don-t-pass-a-box-to-pixman_region32_con.patch \
    file://0076-input-send-focus-events-to-the-focused-client-when-r.patch \
    file://0077-fbdev-Remove-unnecessary-semicolon.patch \
    file://0078-ivi-shell-add-README-for-ivi-shell.patch \
    file://0079-protocol-add-interface-ivi_application-extension.patch \
    file://0080-ivi-shell-add-IVI-layout-APIs.patch \
    file://0081-ivi-shell-add-the-shell-plugin-for-In-Vehicle-Infota.patch \
    file://0082-protocol-ivi-hmi-controller-protocol-to-set-up-IVI-s.patch \
    file://0083-ivi-shell-a-reference-implementation-how-to-use-ivi-.patch \
    file://0084-clients-a-reference-implementation-of-UI-client-how-.patch \
    file://0085-data-add-reference-image-files-for-weston-ivi-shell-.patch \
    file://0086-ivi-shell-a-reference-of-weston.ini-for-ivi-shell-an.patch \
    file://0087-clients-support-ivi-application.xml-for-clients-simp.patch \
    file://0088-clients-support-ivi-application.xml-for-clients-simp.patch \
    file://0089-clients-support-ivi-application.xml-for-clients-wind.patch \
    file://0090-ivi-shell-reference-implementation-of-input-panel-fo.patch \
    file://0091-ivi-shell-clean-up-transition-animation-code.patch \
    file://0092-ivi-shell-reordering-method-in-ivi_layout.c.patch \
    file://0093-ivi-shell-several-ivi-layout-interfaces-moved-accros.patch \
    file://0094-ivi-shell-clean-up-unnecesary-header.patch \
    file://0095-ivi-shell-make-ivi-layout.c-as-a-part-of-ivi-shell.s.patch \
    file://0096-ivi-shell-rewrite-controller-API.patch \
    "

# We use git because we need binary diff support or the above patches (png files)
PATCHTOOL = 'git'
#
# or use this.
#  SRCREV = "299f340dc6fd34f025034d1b271f8265c1f5af97"
#  SRC_URI_append += "git://github.com/ntanibata/weston-ivi-shell.git;protocol=https;branch=weston-ivi-shell-1.6.0-WIP-14"

# [PATCH] Add the processing which has notice of click event from compositor
# Signed-off-by: Nobuhiko Tanibata <NOBUHIKO_TANIBATA at xddp.denso.co.jp>
SRC_URI_append = " file://ivi-shell-click-event.patch"

# [PATCH] Enable/disable default virtual keyboard
# Signed-off-by: Imran Zaman <imran.zaman@intel.com>
SRC_URI_append = " file://Enable-disable-default-virtual-keyboard.patch"
EXTRA_OECONF += "--disable-default-vkb"

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
