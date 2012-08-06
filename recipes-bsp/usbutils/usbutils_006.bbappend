# After usr merge bash is in bindir
do_install_append () {
	sed -i 's:/bin/bash:/usr/bin/bash:' ${D}/${bindir}/usb-devices
}
