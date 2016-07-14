SUMMARY = "Genivi CommonAPI-SomeIP"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost common-api-c++ vsomeip"

SRCREV = "e2b0e5cbf9a36be6896c5cef7b1ba4857e7111c4"
SRC_URI = "git://git.projects.genivi.org/common-api/cpp-someip-runtime.git;protocol=http "

S = "${WORKDIR}/git"

inherit cmake lib_package gitpkgv

EXTRA_OECMAKE += "-DUSE_INSTALLED_COMMONAPI=ON"
CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

FILES_${PN}-dev += "${libdir}/cmake"

RDEPENDS_${PN}-dev = "vsomeip-dev"

BBCLASSEXTEND = "nativesdk"
