base_bindir = "/usr/bin"
base_sbindir = "/usr/sbin"
base_libdir = "/usr/lib"

# Repare broken symlinks
do_install_append () {
        install -dm755 ${D}${base_bindir}
        install -dm755 ${D}${base_sbindir}
        # add symlinks to kmod
        ln -sf ../..${base_bindir}/kmod ${D}${base_bindir}/lsmod
        for tool in insmod rmmod depmod modinfo modprobe; do
                ln -sf ../..${base_bindir}/kmod ${D}${base_sbindir}/${tool}
        done
        # configuration directories
        install -dm755 ${D}${base_libdir}/depmod.d
        install -dm755 ${D}${base_libdir}/modprobe.d
        install -dm755 ${D}${sysconfdir}/depmod.d
        install -dm755 ${D}${sysconfdir}/modprobe.d

        # install depmod.d file for search/ dir
        install -Dm644 "${WORKDIR}/depmod-search.conf" "${D}${base_libdir}/depmod.d/search.conf"
}
