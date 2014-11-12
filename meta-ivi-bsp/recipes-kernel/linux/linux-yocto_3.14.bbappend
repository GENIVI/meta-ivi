# Find defconfig path
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# Kernel configs
SRC_URI_append_beagleboard  = " file://beagle_qemu.cfg"
SRC_URI_append_vexpressa9   = " file://vexpress_a9.cfg"
SRC_URI_append_qemux86      = " file://qemux86.cfg"
SRC_URI_append_qemux86-64   = " file://qemux86_64.cfg"

# vexpress-a9 support
KMACHINE_vexpressa9 = "beagleboard"
SRCREV_machine_vexpressa9 ?= "${AUTOREV}"
COMPATIBLE_MACHINE_vexpressa9 = "vexpressa9"
