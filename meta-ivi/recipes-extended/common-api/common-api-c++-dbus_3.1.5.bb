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

do_configure_prepend() {
  sed -i '/ set(${var} /c  set(ABSOLUTE_${var} "${CMAKE_INSTALL_PREFIX}/${${var}}") ' ${S}/CMakeLists.txt
  sed -i '/file(RELATIVE_PATH /c  file(RELATIVE_PATH REL_INCLUDE_DIR "${ABSOLUTE_INSTALL_CMAKE_DIR}" "${ABSOLUTE_INSTALL_INCLUDE_DIR}") ' ${S}/CMakeLists.txt
}

inherit cmake lib_package pkgconfig
FILES_${PN}-dev += "${libdir}/cmake"
