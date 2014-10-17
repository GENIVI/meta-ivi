FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = " file://systemd_common_auth_instead_of_system_auth.patch"
