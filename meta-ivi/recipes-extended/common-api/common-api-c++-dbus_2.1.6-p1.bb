SUMMARY = "CommonAPI-DBus"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "3372155b32a2cf3b05e1a2a13f6f8413069de33f"
SRC_URI = "git://git.projects.genivi.org/ipc/common-api-dbus-runtime.git;nobranch=1;protocol=http"
S = "${WORKDIR}/git"

DEPENDS = "common-api2 dbus"

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

inherit autotools lib_package pkgconfig
