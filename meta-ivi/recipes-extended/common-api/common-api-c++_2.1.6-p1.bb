SUMMARY = "CommonAPI"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PROVIDES = "common-api2"

SRCREV = "5a8007f50b82c30535c3f51af5f23b265d594c27"
SRC_URI = "git://git.projects.genivi.org/ipc/common-api-runtime.git;nobranch=1;protocol=http"
S = "${WORKDIR}/git"

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

inherit autotools lib_package pkgconfig
