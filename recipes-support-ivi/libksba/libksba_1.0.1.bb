DESCRIPTION = "KSBA is a library designed to build software basedon the X.509 and CMS protocols."

HOMEPAGE = "http://www.gnupg.org/"
SECTION = "system/libraries"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libgcrypt libgpg-error"

SRC_URI = "http://mirrors.dotsrc.org/gcrypt/libksba/libksba-1.0.1.tar.bz2"

SRC_URI[md5sum] = "43646aa414f28e4962f8db138efbf249"
SRC_URI[sha256sum] = "e8bc9709e28c6826f275da2107955587711dd88471e66cf492a4b3b36c074f71"

inherit autotools gettext binconfig
