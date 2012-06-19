# Move /sbin and /bin in /usr
do_install_append () {
	mkdir -p ${D}${sbindir}
	mkdir -p ${D}${bindir}
	mv ${D}${base_sbindir}/* ${D}${sbindir}
	mv ${D}${base_bindir}/* ${D}${bindir}
	rmdir ${D}${base_sbindir}
	rmdir ${D}${base_bindir}
}

# Update alternative with new path
ALTERNATIVE_NAME = "getty"
ALTERNATIVE_LINK = "${sbindir}/getty"
ALTERNATIVE_PATH = "${sbindir}/getty.${BPN}"
ALTERNATIVE_PRIORITY = "80"
