DESCRIPTION = "IPC library used by GnuPG and GPGME"
HOMEPAGE = "http://www.gnupg.org/related_software/libassuan/"
BUGTRACKER = "https://bugs.g10code.com/gnupg/index"

LICENSE = "GPLv2 & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LESSER;md5=a6f89e2100d9b6cdffcea4f398e37343 "

DEPENDS = "libgpg-error pth"

SRC_URI = "ftp://ftp.gnupg.org/gcrypt/libassuan/libassuan-${PV}.tar.bz2"

SRC_URI[md5sum] = "6109ea5c8c34ee5591afbd4618dd5ab4"
SRC_URI[sha256sum] = "b928acbf04776803699748ae81f1a4e49c68f3102129248cd7562ce194b462b1"

inherit autotools binconfig


DEFAULT_PREFERENCE = "1"

do_configure_prepend() {
    sh autogen.sh
}
