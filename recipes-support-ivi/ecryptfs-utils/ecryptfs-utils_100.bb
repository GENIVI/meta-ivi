DESCRIPTION = "eCryptfs: A stacked cryptographic filesystem for Linux"
SECTION = "base"
LICENSE = "GPLv2"
DEPENDS = "intltool-native keyutils libgcrypt libpam openssl"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://launchpad.net/ecryptfs/trunk/100/+download/ecryptfs-utils_100.orig.tar.gz \
           file://ecryptfs-fix-disable-nss.patch \
           file://ecryptfs.service \
          "
SRC_URI[md5sum] = "efaa5eabbd368268874536036397f73b"
SRC_URI[sha256sum] = "a31cb155483d44a59f1506ab53990ac36f81630e0cb3fd2f4dcba35fab614689"

PR = "r3"

inherit autotools systemd

SYSTEMD_SERVICE = "ecryptfs.service"

EXTRA_OECONF += "--disable-nss --disable-pywrap --enable-openssl --prefix=/ --datarootdir=/usr/share"
EXTRA_OEMAKE += "'CFLAGS+= -lgcrypt '"

FILES_${PN} += " \
               ${libdir}/ecryptfs/* \
               ${libdir}/security/pam_ecryptfs.so \
               ${systemd_unitdir}/system/ecryptfs.service \
               "

FILES_${PN}-doc += " \
                   /share/locale/* \
                   "

FILES_${PN}-dbg += "${libdir}/ecryptfs/.debug \
                    ${libdir}/security/.debug \
                   "
do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/ecryptfs.service ${D}${systemd_unitdir}/system
    fi
}
