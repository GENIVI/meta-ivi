SUMMARY = "A new software bus for rapidly accessing vehicle data"
DESCRIPTION = "VSI allows very flexible high speed communication of \
data between threads or tasks on a single processor node system."
HOMEPAGE = "https://github.com/GENIVI/vehicle_signal_interface"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI = "git://github.com/GENIVI/vehicle_signal_interface.git;protocol=https"
SRCREV = "d6c4ef72e775a7b0fd88f38ad5db5ad3df9d881b"

S = "${WORKDIR}/git"

inherit cmake

# Python interface is enabled by default
# but is unlikely needed by most users
EXTRA_OECMAKE += "-DBUILD_PYTHON=OFF"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install_append() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/src/sharedMemory.h ${D}${includedir}
    install -m 0644 ${S}/src/signals.h ${D}${includedir}
    install -m 0644 ${S}/src/vsi.h ${D}${includedir}
    install -m 0644 ${S}/src/vsi_core_api.h ${D}${includedir}
}

PACKAGE_${PN}-dev = "\
    ${includedir} \
"
