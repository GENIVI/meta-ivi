bindir = "/usr/bin"
sbindir = "/usr/sbin"

ALTERNATIVE_LINK_NAME[sh] = "${bindir}/sh"
ALTERNATIVE_TARGET[sh] = "${bindir}/bash"

do_install_append () {
    # Move /usr/bin/bash to /bin/bash, if need
    if [ "${base_bindir}" != "${bindir}" ]; then
        mkdir -p ${D}${bindir}
        mv ${D}${base_bindir}/bash ${D}${bindir}
        rmdir ${D}${base_bindir}
    fi
}
