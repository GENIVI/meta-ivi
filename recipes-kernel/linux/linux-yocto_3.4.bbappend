# find defconfig path
FILESEXTRAPATHS := "${THISDIR}/${PN}"

# Kernel configs
SRC_URI_append_beagleboard = " file://beagle_qemu.cfg \
                               "

SRC_URI_append_vexpressa9 = " file://vexpress_a9.cfg  \
                               "

SRC_URI += "file://0001-net-bus-add-the-AF_BUS-socket-address-family.patch"

#Binutils is gplv3 so we remove it from 
PERFDEPENDS = "virtual/${MLPREFIX}libc:do_populate_sysroot ${MLPREFIX}elfutils:do_populate_sysroot"

KBRANCH_vexpressa9 = "standard/beagleboard"
SRCREV_machine_vexpressa9 ?= "AUTOINC"
COMPATIBLE_MACHINE_vexpressa9 = "vexpressa9"

