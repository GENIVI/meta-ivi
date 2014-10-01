PACKAGECONFIG_remove = "bluetooth"
PACKAGECONFIG[bluetooth] = "--enable-bluetooth, --disable-bluetooth, bluez5"
RDEPENDS_${PN}_remove = "bluez4"
RDEPENDS_${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'bluetooth', 'bluez5', '', d)}"

PR = "r1"