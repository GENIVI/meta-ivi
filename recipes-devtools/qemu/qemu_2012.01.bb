require recipes-devtools/qemu/qemu.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913"

PR = "r0"

FILESPATH = "${FILE_DIRNAME}/qemu-${PV}"
FILESDIR = "${WORKDIR}"

# Tag 2012.01
SRCREV = "4b6667af6cd1a58f4d43f3baae432723f47d28fb"

SRC_URI = "\
    git://git.linaro.org/qemu/qemu-linaro.git;protocol=git \
    file://powerpc_rom.bin \
    file://no-strip.patch \
    file://fallback-to-safe-mmap_min_addr.patch \
    file://larger_default_ram_size.patch \
    "
# Only use the GL passthrough patches for native/nativesdk versions
QEMUGLPATCHES = "\
    "
SRC_URI_append_virtclass-native = "\
    ${QEMUGLPATCHES} \
    "

SRC_URI_append_virtclass-nativesdk = "\
    ${QEMUGLPATCHES} \
    file://glflags.patch \
    "
#SRC_URI[md5sum] = "34f17737baaf1b3495c89cd6d4a607ed"
#SRC_URI[sha256sum] = "7705b14d9b8e4df4a0b1790980e618084261e8daef0672a1aa7a830a0f3db5ba"

S = "${WORKDIR}/git"