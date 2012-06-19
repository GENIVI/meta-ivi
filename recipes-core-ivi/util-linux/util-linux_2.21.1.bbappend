base_libdir = "${libdir}"
base_bindir = "${bindir}"
base_sbindir = "${sbindir}"

do_install_append () {
	ln -sf swapon   ${D}${base_sbindir}/swapoff
}

FILES_util-linux-libblkid-dev = "${base_libdir}/libblkid.so ${base_libdir}/libblkid.la ${includedir}/blkid ${libdir}/pkgconfig/blkid.pc"
FILES_util-linux-libuuid-dev = "${base_libdir}/libuuid.so ${base_libdir}/libuuid.la ${includedir}/uuid ${libdir}/pkgconfig/uuid.pc"
