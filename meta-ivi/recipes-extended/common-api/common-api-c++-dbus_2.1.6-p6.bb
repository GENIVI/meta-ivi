SUMMARY = "CommonAPI-DBus"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

BRANCH ?= "maintain/2.1.6"
TAG ?= "${PV}"
SRC_URI = " \
    git://git.projects.genivi.org/ipc/common-api-dbus-runtime.git;branch=${BRANCH};tag=${TAG} \
    file://fix-with-automake-1-15.patch \
    "
S = "${WORKDIR}/git"

DEPENDS = "common-api2 dbus"

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

inherit autotools lib_package pkgconfig
