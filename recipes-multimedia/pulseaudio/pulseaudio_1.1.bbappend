DEPENDS = "libatomics-ops liboil libsamplerate0 libsndfile1 libtool ${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 gtk+', '', d)} \
           udev alsa-lib glib-2.0 dbus consolekit ${@base_contains('DISTRO_FEATURES', 'bluetooth', 'bluez4', '', d)} libjson gdbm speex libxml-parser-perl-native"
