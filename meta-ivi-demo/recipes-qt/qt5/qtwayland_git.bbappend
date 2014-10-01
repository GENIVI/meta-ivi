FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

QT_MODULE_BRANCH = "5.4"

SRC_URI_append = " file://disable_xcomposite_egl_qt_wayland_client_buffer_integration.patch"

DEPENDS_append_koelsch = " libegl gles-user-module"
