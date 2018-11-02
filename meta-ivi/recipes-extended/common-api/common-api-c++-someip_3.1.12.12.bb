SUMMARY = "Genivi CommonAPI-SomeIP"
SECTION = "libs"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost common-api-c++ vsomeip"

SRCREV = "64f0cb0133b23ff13c48c377546cdaef45b3d9ce"
SRC_URI = "git://github.com/GENIVI/capicxx-someip-runtime.git;protocol=https \
    "
S = "${WORKDIR}/git"

inherit cmake lib_package gitpkgv

EXTRA_OECMAKE += "-DUSE_INSTALLED_COMMONAPI=ON"

FILES_${PN}-dev += "${libdir}/cmake"
RDEPENDS_${PN}-dev = "vsomeip-dev"

BBCLASSEXTEND = "nativesdk"
