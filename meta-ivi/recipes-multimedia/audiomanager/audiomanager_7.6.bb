SUMMARY = "Genivi AudioManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "multimedia"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENCE;md5=f164349b56ed530a6642e9b9f244eec5"

PR = "r0"

DEPENDS = "common-api-c++-dbus dlt-daemon sqlite3 dbus node-state-manager"

inherit gettext cmake pkgconfig systemd
_BPN = "AudioManager"
SRCREV = "bcaeed20bbbb4375ae925d507821d2bef64e737b"
SRC_URI = " git://github.com/GENIVI/${_BPN}.git;protocol=https \
    file://${_BPN}.service \
    file://setup_amgr.sh \
    "
S = "${WORKDIR}/git"


SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${_BPN}.service"
SYSTEMD_AUTO_ENABLE = "disable"

OECMAKE_CXX_FLAGS +="-ldl"
EXTRA_OECMAKE = " -DWITH_DBUS_WRAPPER=ON -DWITH_TESTS=OFF"

FILES_${PN} = " \
    ${bindir}/* \
    ${systemd_unitdir}/${_BPN}.service \
    ${systemd_unitdir}/scripts/setup_amgr.sh \
    "
FILES_${PN}-dev += " \
    ${libdir}/* \
    "

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        mkdir -p ${D}${systemd_unitdir}/scripts/
        install -m 0755 ${WORKDIR}/setup_amgr.sh ${D}${systemd_unitdir}/scripts/setup_amgr.sh
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/${_BPN}.service ${D}${systemd_unitdir}/system
    fi

    perl -pi -e 's|set_and_check\(|#set_and_check\(|' ${D}${libdir}/cmake/*/*.cmake

}
