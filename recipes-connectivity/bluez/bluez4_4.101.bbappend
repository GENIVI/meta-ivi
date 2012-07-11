PRINC = "2"

DEPENDS += " ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${libdir}/systemd/system/"
FILES_${PN}-dbg += " ${libexecdir}/udev/.debug/ "

# Systemd service needs systemd
RDEPENDS_${PN}-systemd = "systemd"

do_configure_append () {
	#udev_get_dev_path(), udev_get_sys_path(), udev_get_run_path()
	#systemd does not allow to configure any of these filesystem paths
	#udev is included in systemd
	sed -i 's:udev_get_sys_path(udev):"/sys":' tools/hid2hci.c
}