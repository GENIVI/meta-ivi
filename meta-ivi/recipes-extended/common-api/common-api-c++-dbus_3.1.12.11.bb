SUMMARY = "CommonAPI-DBus"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r0"

DEPENDS = "commonapi3 dbus"
REQUIRES = "commonapi3 dbus"

SRCREV = "9f85f0f18d9ca436fb618769149ee02e78fd283b"
SRC_URI = "git://github.com/GENIVI/capicxx-dbus-runtime.git;protocol=https"
S = "${WORKDIR}/git"

inherit cmake lib_package pkgconfig
FILES_${PN}-dev += "${libdir}/cmake"

EXTRA_OECMAKE = "-DINSTALL_LIB_DIR:PATH=${baselib} \
    -DINSTALL_CMAKE_DIR:PATH=${baselib}/cmake/CommonAPI-DBus \
    "
