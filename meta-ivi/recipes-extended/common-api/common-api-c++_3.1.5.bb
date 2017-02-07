SUMMARY = "CommonAPI"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PROVIDES = "commonapi3"
PR = "r1"

DEPENDS = "dlt-daemon"

inherit cmake lib_package pkgconfig

SRCREV = "77d9cdaf6acee03a1e360e0e1a3cd0b01a95b407"

SRC_URI = "git://github.com/GENIVI/capicxx-core-runtime.git;protocol=https \
    file://0001-common-api-runtime-replace-hard-coded-lib-dir-by-var.patch \
    "
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=/usr \
    -DINSTALL_LIB_DIR:PATH=${baselib} \
    -DINSTALL_CMAKE_DIR:PATH=${baselib}/cmake/CommonAPI \
    "

FILES_${PN}-dev += "${libdir}/cmake"
