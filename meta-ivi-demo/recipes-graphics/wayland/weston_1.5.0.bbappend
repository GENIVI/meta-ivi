FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "file://weston_ini_add_xwayland_module.patch"

EXTRA_OECONF_append =" --enable-xwayland --enable-xwayland-test"
