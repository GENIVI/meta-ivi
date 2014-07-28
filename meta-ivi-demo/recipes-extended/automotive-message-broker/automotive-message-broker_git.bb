SUMMARY = "automotive message broker"
DESCRIPTION = "Automotive-message-broker abstracts the details of the network \
away from applications and provides a standard API for applications to easily \
get the required information"

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=b42382de5d854b9bb598acf2e8827de3"

SRCREV = "6ce6bfb0faa06c4f715408506c1014aa0d5c34fc"

PV = "0.11.810+git${SRCPV}"

DEPENDS = "qtbase boost json-c libtool gpsd"

SRC_URI = "git://github.com/otcshare/automotive-message-broker;protocol=http \
           file://ambd.service \
          "

SRC_URI[md5sum] = "3b6015f3e8e1522bfde9dfb29fda73cd"
SRC_URI[sha256sum] = "8c2101a44ce4ce77d874667bbaffd4e59992bd59de8388a08dd68cb1bff155e3"


S = "${WORKDIR}/git"
inherit cmake systemd cmake_qt5

SYSTEMD_SERVICE = "ambd.service"

EXTRA_OECMAKE += " -Denable_icecc=OFF -Dqtmainloop=On"

do_compile_prepend() {
    # Remove ";" from compile command after fpermissive
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/ambd/CMakeFiles/ambd.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/ambd/CMakeFiles/ambd.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/lib/CMakeFiles/amb.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/lib/CMakeFiles/amb.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/CMakeFiles/examplesinkplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/CMakeFiles/examplesinkplugin.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/CMakeFiles/examplesourceplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/CMakeFiles/examplesourceplugin.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/testplugin/CMakeFiles/testplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/testplugin/CMakeFiles/testplugin.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/common/CMakeFiles/amb-plugins-common.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/common/CMakeFiles/amb-plugins-common.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/dbus/CMakeFiles/dbussinkplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/dbus/CMakeFiles/dbussinkplugin.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/demosink/CMakeFiles/demosinkplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/demosink/CMakeFiles/demosinkplugin.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/wheel/CMakeFiles/wheelsourceplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/plugins/wheel/CMakeFiles/wheelsourceplugin.dir/link.txt
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/ambd/CMakeFiles/qtmainloopplugin.dir/flags.make
    sed -i 's/fpermissive;/fpermissive /' ${S}/../build/ambd/CMakeFiles/qtmainloopplugin.dir/link.txt

}

do_install_append() {
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/ambd.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}/ambd.service"
