DESCRIPTION = "OBEX Server and Client"
DEPENDS = "libical bluez4"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "files://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

SRC_URI = "http://www.kernel.org/pub/linux/bluetooth/obexd-${PV}.tar.gz"
SRC_URI[md5sum] = "851f4d74e6824f3928168375dc3ad427"
SRC_URI[sha256sum] = "9140378780bcf37a3773b68e088d0b7c3eb43d0880059fbe2a19a3041000fc97"

inherit autotools

PACKAGES =+ "obex-client obex-plugins"

FILES_${PN} += "${datadir}/dbus-1/services/${PN}.service"
FILES_obex-client = "${libexecdir}/obex-client \
                     ${datadir}/dbus-1/services/obex-client.service"
# currently the plugins are empty
FILES_obex-plugins = "${libdir}/obex/plugins"
