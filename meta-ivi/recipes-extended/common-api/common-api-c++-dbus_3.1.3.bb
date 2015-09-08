SUMMARY = "CommonAPI-DBus"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r1"
DEPENDS = "commonapi3 dbus"
REQUIRES = "commonapi3 dbus"

SRC_URI = "git://git.projects.genivi.org/ipc/common-api-dbus-runtime.git;branch=master;tag=${PV}" 

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=/usr "
CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

do_configure_prepend() {
  sed -i '/ set(${var} /c  set(ABSOLUTE_${var} "${CMAKE_INSTALL_PREFIX}/${${var}}") ' ${S}/CMakeLists.txt
  sed -i '/file(RELATIVE_PATH /c  file(RELATIVE_PATH REL_INCLUDE_DIR "${ABSOLUTE_INSTALL_CMAKE_DIR}" "${ABSOLUTE_INSTALL_INCLUDE_DIR}") ' ${S}/CMakeLists.txt
}

inherit cmake lib_package pkgconfig
FILES_${PN}-dev += "${libdir}/cmake"
