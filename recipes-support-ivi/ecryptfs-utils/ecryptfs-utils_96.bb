DESCRIPTION = "eCryptfs: A stacked cryptographic filesystem for Linux"
SECTION = "base"
LICENSE = "GPLv2"
DEPENDS = "keyutils libgcrypt libpam"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://launchpad.net/ecryptfs/trunk/96/+download/ecryptfs-utils_96.orig.tar.gz \
           file://ecryptfs-fix-disable-nss.patch \
           file://ecryptfs.service \
          "
SRC_URI[md5sum] = "4f92c9f6e8f62ac00ca7e2c4e480f1d7"
SRC_URI[sha256sum] = "04c43b6e7a2f6b3644a24bcf718bfde6384e74bb4266c053ac78b9cc852f2c0c"


inherit autotools

EXTRA_OECONF += "--disable-nss --disable-pywrap --enable-openssl --prefix=/ --datarootdir=/usr/share"
EXTRA_OEMAKE += "'CFLAGS+= -lgcrypt '"

FILES_${PN} += " \
               ${libdir}/ecryptfs/* \
               ${libdir}/security/pam_ecryptfs.so \
               "

FILES_${PN}-doc += " \
                   /share/locale/* \
                   "

FILES_${PN}-dbg += "${libdir}/ecryptfs/.debug \
                    ${libdir}/security/.debug \
                   "
PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "/lib/systemd/system/"

do_install_append() {
        mkdir -p ${D}/lib/systemd/system
        install -m 644 ${WORKDIR}/ecryptfs.service ${D}/lib/systemd/system
}

# Skip useless warning as we have /usr and / on the same partition
WARN_QA = "ldflags useless-rpaths rpaths staticdev"
