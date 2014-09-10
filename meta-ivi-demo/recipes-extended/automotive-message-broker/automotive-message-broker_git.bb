SUMMARY = "automotive message broker"
DESCRIPTION = "Automotive-message-broker abstracts the details of the network \
away from applications and provides a standard API for applications to easily \
get the required information"
HOMEPGAE = "https://github.com/otcshare/automotive-message-broker/wiki"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=b42382de5d854b9bb598acf2e8827de3"

DEPENDS = "glib-2.0 util-linux sqlite3 qtbase boost json-c libtool gpsd"

PV = "0.11.811+git${SRCPV}"

SRC_URI = "git://github.com/otcshare/automotive-message-broker;protocol=http \
           file://ambd.service \
           file://0001-CMakeLists.txt-replaced-APPEND-with-SET.patch \
          "

SRC_URI[md5sum] = "3b6015f3e8e1522bfde9dfb29fda73cd"
SRC_URI[sha256sum] = "8c2101a44ce4ce77d874667bbaffd4e59992bd59de8388a08dd68cb1bff155e3"

SRCREV = "e848fab97e7e22423decf19eba208c4d9ee40325"
S = "${WORKDIR}/git"

inherit cmake systemd cmake_qt5

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ambd.service"

EXTRA_OECMAKE += " -Denable_icecc=OFF -Dqtmainloop=On"

do_install_append() {
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/ambd.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}/ambd.service"
