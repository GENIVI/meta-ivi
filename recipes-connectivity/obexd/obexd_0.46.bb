DESCRIPTION = "OBEX Server and Client"
DEPENDS = "libical bluez4"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "files://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

SRC_URI = "http://www.kernel.org/pub/linux/bluetooth/obexd-${PV}.tar.gz"
SRC_URI[md5sum] = "54fa544e208830fecce8ef6f3c190cb3"
SRC_URI[sha256sum] = "961328f9dbb8f8ceef443c873a881706146071158f539b897c27ca965578d231"

inherit autotools

PACKAGES =+ "obex-client obex-plugins"

FILES_${PN} += "${datadir}/dbus-1/services/${PN}.service"
FILES_obex-client = "${libexecdir}/obex-client \
                     ${datadir}/dbus-1/services/obex-client.service"
# currently the plugins are empty
FILES_obex-plugins = "${libdir}/obex/plugins"