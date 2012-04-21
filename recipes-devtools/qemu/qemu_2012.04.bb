require recipes-devtools/qemu/qemu.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913"

PR = "r0"

FILESPATH = "${FILE_DIRNAME}/qemu-${PV}"
FILESDIR = "${WORKDIR}"

# Tag 2012.04
SRCREV = "cc9364d58d474ee6ebc78d98ad3f24afe65b95a6"

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
S = "${WORKDIR}/git"