PRINC = "3"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "bluetooth.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_configure_append () {
	#udev_get_dev_path(), udev_get_sys_path(), udev_get_run_path()
	#systemd does not allow to configure any of these filesystem paths
	#udev is included in systemd
	sed -i 's:udev_get_sys_path(udev):"/sys":' tools/hid2hci.c
}