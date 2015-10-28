FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI += "file://remove-syslog.cfg"

# Defconfig doesn't have syslog activated
# Adapt if needed in the future
SYSTEMD_PACKAGES_remove = "${PN}-syslog"
INITSCRIPT_PACKAGES_remove = "${PN}-syslog"
ALTERNATIVE_${PN}-syslog_remove = "syslog-conf"
