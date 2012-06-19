# Find "files" directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC = "1"

INITSCRIPT_NAME = ""
INITSCRIPT_PARAMS = ""

# Add patch to revert the hard-coded patch of sbin while installing
# mount utils
SRC_URI_append = " file://remove-hard-coded-path-of-sbin.patch"

do_install_append () {
	# Remove init scripts
	rm -r ${D}${sysconfdir}/init.d
}
