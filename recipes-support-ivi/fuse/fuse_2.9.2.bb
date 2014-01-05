SUMMARY = "Filesystem in Userspace"
DESCRIPTION = "FUSE (Filesystem in Userspace) is a simple interface for \
userspace programs to export a virtual filesystem to the Linux kernel"
HOMEPAGE = "http://fuse.sf.net"
SECTION = "libs"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/fuse/fuse-${PV}.tar.gz \
           file://gold-unversioned-symbol.patch \
           file://aarch64.patch \
          "
SRC_URI[md5sum] = "7d80d0dc9cc2b9199a0c53787c151205"
SRC_URI[sha256sum] = "81a728fb3f87da33063068735e2fb7e2cd89df207d32687d3d3278385279cefc"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-kernel-module"

PACKAGES =+ "fuse-utils-dbg fuse-utils libulockmgr libulockmgr-dev libulockmgr-dbg"

RRECOMMENDS_${PN} = "kernel-module-fuse"

FILES_${PN} += "${libdir}/libfuse.so.*"
FILES_${PN}-dev += "${libdir}/libfuse*.la"

FILES_libulockmgr = "${libdir}/libulockmgr.so.*"
FILES_libulockmgr-dev += "${libdir}/libulock*.la"
FILES_libulockmgr-dbg += "${libdir}/.debug/libulock*"

# Forbid auto-renaming to libfuse-utils
FILES_fuse-utils = "${bindir} ${base_sbindir}"
FILES_fuse-utils-dbg = "${bindir}/.debug ${base_sbindir}/.debug"
DEBIAN_NOAUTONAME_fuse-utils = "1"
DEBIAN_NOAUTONAME_fuse-utils-dbg = "1"

do_install_append() {
    rm -rf ${D}${base_prefix}/dev

    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)} && \
        ! ${@base_contains('DISTRO_FEATURES','sysvinit','true','false',d)} && \
        test -d "${D}/etc/init.d"; then
            rm -rf ${D}/etc/init.d/
    fi
}
