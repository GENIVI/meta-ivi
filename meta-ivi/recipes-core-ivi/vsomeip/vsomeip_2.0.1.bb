SUMMARY = "the implementation of SOME/IP"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost dlt-daemon"

SRCREV = "2b7442a4c2452f8f3bd9e0f09f829478256d39af"
SRC_URI = "git://git.projects.genivi.org/vSomeIP.git;protocol=http \
    file://build_fixup.patch \
    "

S = "${WORKDIR}/git"

inherit cmake lib_package gitpkgv

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

PACKAGES_remove += "${PN}-bin"
FILES_${PN} += "${bindir}/vsomeipd"
FILES_${PN}-dev += "${libdir}/cmake"

BBCLASSEXTEND = "nativesdk"
