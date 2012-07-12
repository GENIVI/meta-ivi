SUMMARY = "Genivi AudioManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "multimedia"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENCE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "DLT-daemon sqlite3 dbus"

SRC_URI = "git://git.genivi.org/srv/git/AudioManager;protocol=git;tag=a1f572cacb1c9b520a7f5271ce75c9ae2f4862c9 \
           file://AudioManager.service file://setup_amgr.sh"

S = "${WORKDIR}/git"
inherit autotools gettext cmake pkgconfig

EXTRA_OECMAKE += "-DWITH_TESTS=OFF"
OECMAKE_CXX_FLAGS +="-ldl"

FILES_${PN} += "${libdir}/audioManager/command/*.so.* \
                ${libdir}/audioManager/control/*.so.* \
                ${libdir}/audioManager/routing/*.so.* \
"
FILES_${PN}-dev += "${libdir}/audioManager/command/*.so \
                    ${libdir}/audioManager/control/*.so \
                    ${libdir}/audioManager/routing/*.so \
"

FILES_${PN}-systemd += "${libdir}/systemd/"

FILES_${PN}-dbg += "${libdir}/audioManager/command/.debug/* \
                    ${libdir}/audioManager/control/.debug/* \
                    ${libdir}/audioManager/routing/.debug/* \
"

PACKAGES =+ "${PN}-systemd"

do_install_append() {
        mkdir -p ${D}/usr/lib/systemd/system/ ${D}/usr/lib/systemd/scripts/
        install -m 0755 ${WORKDIR}/AudioManager.service ${D}/usr/lib/systemd/system/AudioManager.service
        install -m 0755 ${WORKDIR}/setup_amgr.sh ${D}/usr/lib/systemd/scripts/setup_amgr.sh
}

ERROR_QA = "debug-deps dev-deps debug-files arch la2 pkgconfig la perms"
