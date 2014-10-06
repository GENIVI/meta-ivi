SUMMARY = "A stacked cryptographic filesystem for Linux"
DESCRIPTION = "eCryptfs stores cryptographic metadata in the header of each \
file written, so that encrypted files can be copied between hosts; the file \
will be decrypted with the proper key in the Linux kernel keyring"
SECTION = "base"
LICENSE = "GPLv2"
DEPENDS = "intltool-native keyutils libgcrypt libpam openssl glib-2.0-native"

PR = "r1"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://launchpad.net/ecryptfs/trunk/${PV}/+download/${BPN}_${PV}.orig.tar.gz \
           file://ecryptfs-fix-disable-nss.patch \
           file://ecryptfs.service \
          "
SRC_URI[md5sum] = "6ae93822bcf0d15470516c30a3deee32"
SRC_URI[sha256sum] = "4a200176542d46439c5297021f2c7fd7343b871c20c6f73f6e6c9fc4e5fd7438"

inherit pkgconfig autotools systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ecryptfs.service"

EXTRA_OECONF += "--disable-nss --disable-pywrap --enable-openssl --prefix=/ --sbindir=/sbin --datarootdir=/usr/share"
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
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/ecryptfs.service ${D}${systemd_unitdir}/system
    fi
}
