PRINC = "1"

# Do't register init scripts
INITSCRIPT_PACKAGES = ""

INITSCRIPT_NAME_${PN}-httpd = ""
INITSCRIPT_NAME_${PN}-syslog = ""
INITSCRIPT_NAME_${PN}-mdev = ""
INITSCRIPT_PARAMS_${PN}-mdev = ""
INITSCRIPT_NAME_${PN}-udhcpd = ""
INITSCRIPT_NAME_${PN}-udhcpc = ""

# Don' install syslog and udhcpc
RRECOMMENDS_${PN} = ""

# Package hwclock init script in a different package
PACKAGES =+ " ${PN}-hwclock "
FILES_${PN}-hwclock = "${sysconfdir}/init.d/hwclock.sh"

# Don't update update-alternatives syslog init script as we don't install it
pkg_postinst_${PN}-syslog () {
	#update-alternatives --install ${sysconfdir}/init.d/syslog syslog-init syslog.${BPN} 50
	update-alternatives --install ${sysconfdir}/syslog-startup.conf syslog-startup-conf syslog-startup.conf.${BPN} 50
}
