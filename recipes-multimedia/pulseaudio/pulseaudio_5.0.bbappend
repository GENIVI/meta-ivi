FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://pulseaudio.service"

PR = "r1"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-server"
SYSTEMD_SERVICE = "pulseaudio.service"

PACKAGECONFIG_remove = "bluez4"
PACKAGECONFIG_append = " bluez5"

RDEPENDS_pulseaudio-module-systemd-login =+ "systemd"
RDEPENDS_pulseaudio-server += "\
        ${@base_contains('DISTRO_FEATURES', 'x11', 'pulseaudio-module-systemd-login', '', d)}"

python __anonymous () {
    '''
    If DISTRO_FEATURES include systemd use pulseaudio-module-systemd-login as a
    replacer for pulseaudio-module-console-kit.
    '''
    distro_features = d.getVar('DISTRO_FEATURES', True).split()
    if 'systemd' in distro_features:
        new_rdeps = []
        old_rdeps = d.getVar('RDEPENDS_pulseaudio-server', True).split()
        for rdep in old_rdeps:
            if rdep != 'pulseaudio-module-console-kit':
                new_rdeps.append(rdep)
        d.setVar('RDEPENDS_pulseaudio-server', ' '.join(new_rdeps))
}

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/pulseaudio.service ${D}${systemd_unitdir}/system
    fi
}
