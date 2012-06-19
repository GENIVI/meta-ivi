DESCRIPTION = "Linux Key Management Utilities"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENCE.GPL;md5=5f6e72824f5da505c1f4a7197f004b45"

SRCREV = "5cd9d711f650e03685ba8bf2099b2df30cf6e71a"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/dhowells/keyutils.git;protocol=git \
          file://keyutils_fix_library_install.patch \
          "
SRC_URI_append_arm += "file://keyutils-arm-remove-m32-m64.patch"

S = "${WORKDIR}/git"

inherit autotools

INSTALL_FLAGS = "BINDIR=/usr/bin SBINDIR=/usr/sbin DESTDIR=${D}"

do_install() {
	oe_runmake ${INSTALL_FLAGS} install
}
