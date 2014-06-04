SUMMARY = "CommonAPI-DBus"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

SRC_URI = "git://git.projects.genivi.org/ipc/common-api-dbus-runtime.git;protocol=http;tag=${PV}"
S = "${WORKDIR}/git"

DEPENDS = "common-api2 dbus"

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

inherit autotools lib_package pkgconfig
