PRINC := "${@int(PRINC) + 4}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://pulseaudio.service "

SYSTEMD_SERVICE = "pulseaudio.service"

# Remove gconf as it triggers a GPLv3 dependency
DEPENDS := "${@oe_filter_out('gconf', '${DEPENDS}', d)}"

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

RDEPENDS_pulseaudio-module-systemd-login =+ "systemd"
RDEPENDS_pulseaudio-server += "\
        ${@base_contains('DISTRO_FEATURES', 'x11', 'pulseaudio-module-systemd-login', '', d)}"

# We use pulseaudio-module-systemd-login so let's drop pulseaudio-module-console-kit
RDEPENDS_pulseaudio-server := "${@oe_filter_out('pulseaudio-module-console-kit', '${RDEPENDS_pulseaudio-server}', d)}"

FILES_${PN}-server += " \
    ${systemd_unitdir}/system/pulseaudio.service \
    "

do_configure_append () {
	# udev_get_dev_path(), udev_get_sys_path(), udev_get_run_path()
	# systemd does not allow to configure any of these filesystem paths
	# udev is included in systemd
	sed -i 's:udev_get_sys_path(udev):"/sys":' src/modules/module-udev-detect.c
	sed -i 's:udev_get_sys_path(udev):"/sys":' src/modules/udev-util.c
	sed -i 's:udev_get_dev_path(u->udev):"/dev":' src/modules/module-udev-detect.c
}

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/pulseaudio.service ${D}${systemd_unitdir}/system
    fi
}