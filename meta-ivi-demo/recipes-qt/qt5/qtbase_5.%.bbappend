FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG_GL = "${@base_contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
