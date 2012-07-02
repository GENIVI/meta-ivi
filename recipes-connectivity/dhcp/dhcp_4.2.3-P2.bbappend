FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI += "file://dhclient@.service \
			"

# Move base_sbindir in sbindir
do_install_append () {
	mv ${D}/${base_sbindir}/dhclient ${D}/${sbindir}
	mv ${D}/${base_sbindir}/dhclient-script ${D}/${sbindir}
	rmdir ${D}/${base_sbindir}
}

FILES_dhcp-client = "${sbindir}/dhclient ${sbindir}/dhclient-script ${sysconfdir}/dhcp/dhclient.conf"

# After usr merge bash is in bindir
do_install_append () {
	sed -i 's:/bin/bash:/usr/bin/bash:' ${D}/${sbindir}/dhclient-script
	# Install systemd service for dhclient
	install -d ${D}/${libdir}/systemd/system
	install -m 644 ${WORKDIR}/dhclient@.service ${D}/${libdir}/systemd/system
}

PACKAGES += "${PN}-client-systemd"
FILES_dhcp-client-systemd = "${libdir}/systemd/system/"
