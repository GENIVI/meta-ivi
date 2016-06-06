SUMMARY = "CommonAPI-DBus"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"
DEPENDS = "commonapi3 dbus"
REQUIRES = "commonapi3 dbus"

SRCREV = "3348a422ffc756b63de5890356383858a898e8b1"
SRC_URI = "git://git.projects.genivi.org/ipc/common-api-dbus-runtime.git"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=/usr "
CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

inherit cmake lib_package pkgconfig
FILES_${PN}-dev += "${libdir}/cmake"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-common-api-dbus-runtime-replace-hard-coded-lib-dir-b.patch"
EXTRA_OECMAKE = "-DINSTALL_LIB_DIR:PATH=${baselib} -DINSTALL_CMAKE_DIR:PATH=${baselib}/cmake/CommonAPI-DBus"
