DESCRIPTION = "LayerManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "environment/base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=071e6b9a5eb9fc5868edf57ce153e5b9"

DEPENDS = "virtual/egl dbus libxcomposite libxdamage"

SRCREV = "5e2f490b40b8b60a0efbd2b244be44d93bc081f6"
PR = "r1"

SRC_URI = " \
    git://git.projects.genivi.org/layer_management.git;protocol=git \
    file://layermanager.service \
    "

S = "${WORKDIR}/git"

inherit gettext cmake systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "layermanager.service"
SYSTEMD_AUTO_ENABLE = "disable"

EXTRA_OECMAKE += "-DWITH_EXAMPLE_SCENE_PROVIDER=ON"

FILES_${PN} += " \
    ${libdir}/lib* \
    ${libdir}/layermanager/lib* \
    ${libdir}/layermanager/communicator/lib* \
    ${libdir}/layermanager/ipcmodules/lib* \
    ${libdir}/layermanager/renderer/lib* \
    ${libdir}/layermanager/renderer/renderer* \
    ${libdir}/layermanager/sceneprovider/* \
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
    ${libdir}/layermanager/sceneprovider/.debug/ \
    "

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/layermanager.service ${D}${systemd_unitdir}/system
    fi
}
