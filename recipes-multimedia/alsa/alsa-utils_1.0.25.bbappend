PRINC = "1"

FILES_alsa-utils-alsactl += " ${libdir}/systemd "

# Install rules in libdir
EXTRA_OECONF += " --with-udev-rules-dir=${libdir}/udev/rules.d "

FILES_alsa-utils-alsactl += " ${libdir}/udev/rules.d "

