DESCRIPTION = "Weston is the reference implementation of a Wayland compositor. \
This particular version we are using contains the IVI-Shell implementation."
HOMEPAGE = "https://github.com/ntanibata/weston-ivi-shell"

SRCREV = "71019824462597a82e71bee913f12165875e9fd2"

SRC_URI_remove = "http://wayland.freedesktop.org/releases/weston-1.4.0.tar.xz"
SRC_URI_prepend = "git://github.com/ntanibata/weston-ivi-shell.git;branch=weston-ivi-shell-1.4.0-v12;protocol=http "
S = "${WORKDIR}/git"

PR = "r1"

FILES_${PN} += "${libdir}/weston/*"
FILES_${PN}-dbg += "${libdir}/weston/.debug/*"
