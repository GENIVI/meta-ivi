DESCRIPTION = "LayerManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "environment/base"

PR = "r1"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=071e6b9a5eb9fc5868edf57ce153e5b9"

DEPENDS = "virtual/egl dbus libxcomposite libxdamage"

SRC_URI = " \
    git://git.projects.genivi.org/layer_management.git;protocol=git;tag=1_0 \
    file://layermanager.service \
    "

# Needed this for imx6 boards to use precompiled EGL libraries
python () {
    if ((d.getVar("MACHINE", True) or "").find("imx6") != -1):
        flags = d.getVar("OECMAKE_CXX_FLAGS", True)
        flags += " -DLINUX"
        d.setVar('OECMAKE_CXX_FLAGS', flags)
}

S = "${WORKDIR}/git"

inherit gettext cmake systemd

SYSTEMD_SERVICE = "layermanager.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} += " \
    ${libdir}/lib* \
    ${libdir}/layermanager/lib* \
    ${libdir}/layermanager/communicator/lib* \
    ${libdir}/layermanager/ipcmodules/lib* \
    ${libdir}/layermanager/renderer/lib* \
    ${libdir}/layermanager/renderer/renderer* \
    ${systemd_unitdir}/system/layermanager.service \
    "

FILES_${PN}-dev = " \
    ${includedir}/* \
    "
FILES_${PN}-staticdev += " \
    ${libdir}/layermanager/static/lib* \
    "
FILES_${PN}-dbg += " \
    ${libdir}/layermanager/.debug/ \
    ${libdir}/layermanager/communicator/.debug/ \
    ${libdir}/layermanager/ipcmodules/.debug/ \
    ${libdir}/layermanager/renderer/.debug/ \
    "

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/layermanager.service ${D}${systemd_unitdir}/system
    fi
}
