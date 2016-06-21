SUMMARY = "Genivi AudioManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "multimedia"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENCE;md5=f164349b56ed530a6642e9b9f244eec5"
PR = "r1"

DEPENDS = "common-api-c++-dbus dlt-daemon sqlite3 dbus node-state-manager"

SRCREV = "8725157e248c6706de59a02996f869b6ccdccb13"
SRC_URI = " \
    git://git.projects.genivi.org/AudioManager.git;branch=master;protocol=http \
    file://0001-audiomanager-fix-lib-install-path-for-multilib.patch \
    file://AudioManager.service \
    file://setup_amgr.sh \
    "
S = "${WORKDIR}/git"

inherit autotools gettext cmake pkgconfig systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "AudioManager.service"
SYSTEMD_AUTO_ENABLE = "disable"

OECMAKE_CXX_FLAGS +="-ldl"
EXTRA_OECMAKE = " -DWITH_TESTS=OFF"

FILES_${PN} = " \
    ${bindir}/* \
    ${systemd_unitdir}/AudioManager.service \
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
        install -m 0644 ${WORKDIR}/AudioManager.service ${D}${systemd_unitdir}/system
    fi

    perl -pi -e \
      's/set_and_check\(CMAKE_MODULE_PATH/#set_and_check\(CMAKE_MODULE_PATH/' \
      ${D}${libdir}/cmake/audiomanagerConfig.cmake
}
