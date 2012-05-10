FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI += "file://0001-systemd-complement-module-console-kit-with-module-sy.patch \
           "

DEPENDS = "libatomics-ops liboil libsamplerate0 libsndfile1 libtool ${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 gtk+', '', d)} \
           udev alsa-lib glib-2.0 dbus  ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', 'consolekit', d)} ${@base_contains('DISTRO_FEATURES', 'bluetooth', 'bluez4', '', d)} libjson gdbm speex libxml-parser-perl-native"

RDEPENDS_pulseaudio-module-systemd-login =+ "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', 'consolekit', d)}"
RDEPENDS_pulseaudio-server = " \
    pulseaudio-module-filter-apply \
    pulseaudio-module-filter-heuristics \
    pulseaudio-module-udev-detect \
    pulseaudio-module-null-sink \
    pulseaudio-module-device-restore \
    pulseaudio-module-stream-restore \
    pulseaudio-module-card-restore \
    pulseaudio-module-augment-properties \
    pulseaudio-module-detect \
    pulseaudio-module-alsa-sink \
    pulseaudio-module-alsa-source \
    pulseaudio-module-alsa-card \
    pulseaudio-module-native-protocol-unix \
    pulseaudio-module-default-device-restore \
    pulseaudio-module-intended-roles \
    pulseaudio-module-rescue-streams \
    pulseaudio-module-always-sink \
    pulseaudio-module-suspend-on-idle \
    pulseaudio-module-${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd-login', 'console-kit', d)} \
    pulseaudio-module-position-event-sounds \
    pulseaudio-module-cork-music-on-phone "

