# Move base_sbindir in sbindir
do_install_append () {
	mv ${D}/${base_sbindir}/dhclient ${D}/${sbindir}
	mv ${D}/${base_sbindir}/dhclient-script ${D}/${sbindir}
	rmdir ${D}/${base_sbindir}
}

FILES_dhcp-client = "${sbindir}/dhclient ${sbindir}/dhclient-script ${sysconfdir}/dhcp/dhclient.conf"

