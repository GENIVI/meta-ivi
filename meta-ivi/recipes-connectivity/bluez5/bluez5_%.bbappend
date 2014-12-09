FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

#
# Service API reference implementation
# Revision: 5.15 (Horizon)
# Homepage: https://collab.genivi.org/wiki/display/genivi/Bluetooth+Stack+Reference+Implementation
# Actual patches copied from:
# https://review.tizen.org/git/?p=platform/upstream/bluez.git;a=commit;h=abb9a439baaaa9cd6f91ba5af8c6e426cdc8ff8e
#
SRC_URI_append = " \
	file://0001-plugins-Add-initial-code-for-service-plugin.patch          \
	file://0002-plugins-service-Add-initial-code.patch                     \
	file://0003-plugins-service-Add-State-property.patch                   \
	file://0004-plugins-service-Add-RemoteUUID-property.patch              \
	file://0005-plugins-service-Add-LocalUUID-property.patch               \
	file://0006-plugins-service-Add-implemention-of-.Connect-method.patch  \
	file://0007-plugins-service-Add-implemention-of-.Disconnect-meth.patch \
	file://0008-plugins-service-Add-Device-property.patch                  \
	file://0009-plugins-service-Add-Version-property.patch                 \
	file://0010-plugins-service-Add-AutoConnect-property.patch             \
	file://0011-plugins-service-Add-Blocked-property.patch                 \
	"

# we copy the EXTRA_OECONF from Poky's bluez5.inc, fix the following QA issue
# on the way and enable the Service API (--enable-service) option.
# WARNING: QA Issue: bluez5: configure was passed unrecognised options:
#                            --with-systemdunitdir [unknown-configure-option]
# Note: this is an issue only with the Poky "dizzy" branch and should be
#       removed in case of future/later Poky versions.
#       Poky rev: 419a37e09565a820fdeb2d49104cc27f6720af5a
EXTRA_OECONF = "\
	--enable-tools \
	--disable-cups \
	--enable-test \
	--enable-datafiles \
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--enable-systemd', '--disable-systemd', d)} \
	--enable-library \
	--enable-service \
"

PACKAGES =+ " libbluetooth-plugins-service "

FILES_libbluetooth-plugins-service = "${libdir}/bluetooth/plugins/*.so"
