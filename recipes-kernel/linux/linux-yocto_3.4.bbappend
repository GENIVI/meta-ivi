PRINC := "${@int(PRINC) + 1}"

# find defconfig path
FILESEXTRAPATHS := "${THISDIR}/${PN}"

# Kernel configs
SRC_URI_append_beagleboard = " \
	file://beagle_qemu.cfg \
	"

SRC_URI_append_vexpressa9 = " \
	file://vexpress_a9.cfg  \
	"

SRC_URI_append_qemux86 = " \
	file://qemux86.cfg  \
	"

KMACHINE_vexpressa9 = "beagleboard"
SRCREV_machine_vexpressa9 ?= "${AUTOREV}"
COMPATIBLE_MACHINE_vexpressa9 = "vexpressa9"
