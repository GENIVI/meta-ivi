PRINC = "1"

INITSCRIPT_PACKAGES = ""
INITSCRIPT_NAME_${PN}-sshd = ""
INITSCRIPT_PARAMS_${PN}-sshd = ""
do_compile_append () {
	install -m 0644 ${WORKDIR}/sshd_config ${S}/
	install -m 0644 ${WORKDIR}/ssh_config ${S}/
}

do_install_append () {
	rm -r ${D}${sysconfdir}/init.d
}

FILES_${PN}-sshd = "${sbindir}/sshd"

