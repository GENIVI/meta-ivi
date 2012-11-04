# find defconfig path
FILESEXTRAPATHS := "${THISDIR}/${PN}"

# Kernel configs
SRC_URI_append_beagleboard = " \
	file://beagle_qemu.cfg \
	"

SRC_URI_append_vexpressa9 = " \
	file://vexpress_a9.cfg  \
	"

SRC_URI += "file://0001-net-bus-add-the-AF_BUS-socket-address-family.patch"

KBRANCH_vexpressa9 = "standard/beagleboard"
SRCREV_machine_vexpressa9 ?= "449f7f520350700858f21a5554b81cc8ad23267d"

COMPATIBLE_MACHINE_vexpressa9 = "vexpressa9"
