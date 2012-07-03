FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC = "1"

SRC_URI += " \
	file://sshd@.service \
	file://sshd.socket \
	file://genkeys.service \
	file://genkeys.sh \
	"

INITSCRIPT_PACKAGES = ""
INITSCRIPT_NAME_${PN}-sshd = ""
INITSCRIPT_PARAMS_${PN}-sshd = ""
do_compile_append () {
	install -m 0644 ${WORKDIR}/sshd_config ${S}/
	install -m 0644 ${WORKDIR}/ssh_config ${S}/
}

do_install_append () {
	rm -r ${D}${sysconfdir}/init.d
	# Install services
	install -d ${D}/${libdir}/systemd/system
	install -m 744 ${WORKDIR}/sshd@.service ${D}/${libdir}/systemd/system
	install -m 744 ${WORKDIR}/sshd.socket ${D}/${libdir}/systemd/system
	install -m 744 ${WORKDIR}/genkeys.service ${D}/${libdir}/systemd/system
	install -d ${D}/${libdir}/systemd/scripts
	install -m 744 ${WORKDIR}/genkeys.sh ${D}/${libdir}/systemd/scripts
}

PACKAGES += "${PN}-systemd"
FILES_${PN}-systemd = "${libdir}/systemd"

FILES_${PN}-sshd = "${sbindir}/sshd \
					${sysconfdir}/ssh/sshd_config"

