# Find "files" directory
FILESEXTRAPATHS := "${THISDIR}/files"

# Patch for removing autostart countdown
SRC_URI_append_imx53qsb += "file://no_delay.patch"
