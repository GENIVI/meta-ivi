SUMMARY = "itzam/C"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LicenseOpenSource.txt;md5=757d512d8e160c938b85a8b6487cd126"

SRC_URI = "http://coyotegulch.scottrobertladd.net/products/itzam/c/libitzam-6.0.4.tar.gz \
           file://libitzam_pc.patch \
          "

SRC_URI[md5sum] = "333f680c21037e32b4000933eae27721"
SRC_URI[sha256sum] = "d5b565a9d96275e3bf39ea2dd0b121a92b578819650a09fc5add4f4113df734d"

PR = "r2"

inherit autotools

do_configure_prepend() {
    # Add  necesary files
    touch ${S}/INSTALL ${S}/NEWS ${S}/README ${S}/AUTHORS ${S}/ChangeLog
}

do_install_prepend() {
    mkdir -p ${D}/usr/lib/pkgconfig
    cp ${B}/libitzam.pc ${D}/usr/lib/pkgconfig
}

FILES_${PN}-dev += "/usr/lib/pkgconfig/*"
