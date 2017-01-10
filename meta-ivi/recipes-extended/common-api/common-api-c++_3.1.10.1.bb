SUMMARY = "CommonAPI"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PROVIDES = "commonapi3"
PR = "r0"

DEPENDS = "dlt-daemon"

inherit cmake lib_package pkgconfig

SRCREV = "436750026fb1a27944584513c7191d1bc83e9f0d"
SRC_URI = "git://github.com/GENIVI/capicxx-core-runtime.git;protocol=https \
    "
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=/usr \
    -DINSTALL_LIB_DIR:PATH=${baselib} \
    -DINSTALL_CMAKE_DIR:PATH=${baselib}/cmake/CommonAPI \
    "

FILES_${PN}-dev += "${libdir}/cmake"
