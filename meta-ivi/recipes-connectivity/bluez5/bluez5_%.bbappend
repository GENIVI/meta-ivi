FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

#
# Service API reference implementation
# Revision: 5.15 (Horizon)
# Homepage: https://collab.genivi.org/wiki/display/genivi/Bluetooth+Stack+Reference+Implementation
# Actual patches copied from:
# http://git.baserock.org/cgit/delta/bluez.git/log/?h=baserock/5.37-19-g28fef24
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
	file://build-fix-up-with-jethro.patch \
	file://0021-core-service-Add-btd_service_get_version.patch \
	file://0022-core-service-Add-btd_service_set_auto_connect.patch \
	file://0023-core-service-Add-btd_service_get_auto_connect.patch \
	file://0024-core-device-Use-service-auto_connect-flag.patch \
	file://0026-core-service-Add-btd_service_set_blocked.patch \
	file://0027-core-service-Add-btd_service_is_blocked.patch \
	"

EXTRA_OECONF += "--enable-service --enable-sixaxis "

PACKAGES =+ " libbluetooth-plugins-service "
FILES_libbluetooth-plugins-service = "${libdir}/bluetooth/plugins/*.so"

do_configure_prepend() {
    if [ -f configure ]; then
       rm -f configure
    fi
}
