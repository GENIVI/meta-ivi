PRINC = "1"

FILES_alsa-utils-alsactl += " ${libdir}/systemd "

# Install rules in libdir
EXTRA_OECONF += " --with-udev-rules-dir=${libdir}/udev/rules.d "

FILES_alsa-utils-alsactl += " ${libdir}/udev/rules.d "

# After usr merge bash is in bindir
do_install_append () {
	sed -i 's:/bin/bash:/usr/bin/bash:' ${D}/${sbindir}/alsaconf
}
