SUMMARY = "Genivi AudioManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "multimedia"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENCE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "common-api-c++-dbus dlt-daemon sqlite3 dbus"

BRANCH = "Intreprid_stable_branch"

SRC_URI = "git://git.projects.genivi.org/AudioManager.git;branch=${BRANCH};tag=${PV} \
           file://0001-src-gen-update-to-CommonAPI-version-2.1.6.patch \
           file://AudioManager.service file://setup_amgr.sh"

S = "${WORKDIR}/git"
inherit autotools gettext cmake pkgconfig systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "AudioManager.service"
SYSTEMD_AUTO_ENABLE = "disable"

EXTRA_OECMAKE += "-DWITH_TESTS=OFF -DUSE_BUILD_LIBS=OFF"
OECMAKE_CXX_FLAGS +="-ldl"

FILES_${PN} += "${libdir}/audioManager/command/*.so* \
                ${libdir}/audioManager/control/*.so* \
                ${libdir}/audioManager/routing/*.so* \
                ${systemd_unitdir}/AudioManager.service \
                ${systemd_unitdir}/scripts/setup_amgr.sh \
"

FILES_${PN}-dbg += "${libdir}/audioManager/command/.debug/* \
                    ${libdir}/audioManager/control/.debug/* \
                    ${libdir}/audioManager/routing/.debug/* \
"
do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        mkdir -p ${D}${systemd_unitdir}/scripts/
        install -m 0755 ${WORKDIR}/setup_amgr.sh ${D}${systemd_unitdir}/scripts/setup_amgr.sh
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/AudioManager.service ${D}${systemd_unitdir}/system
    fi
}
