inherit autotools cmake

SRCREV = "${AUTOREV}"
PV = "1"
PR = "r1"

SRC_URI = "git://git.projects.genivi.org/lbs/navigation-application.git \
           file://remove_amb_link_path.patch \
           file://logreplayerconfig \
           file://pregenerated_images_and_style_sheets_1024x768.tar.bz2 \
           file://allow_qt_moc_from_build_system.patch \
          "
SRC_REV = "dcf981a6b9997792a526e5710a4e067245adcd60"

DEPENDS = "automotive-message-broker navit navigation-service qtbase"
RDEPENDS_${PN} = "qtquickcontrols-qmlplugins"

S = "${WORKDIR}/git"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2e0f5070190199ece7f69c3c14e2e7af"

EXTRA_CMAKEFLAGS = "-DLM=0 -Dgenerated_api_DIR=${STAGING_INCDIR}/navigation-service"

do_configure() {
    cd ${S}/src/genivilogreplayer && cmake .
    cd ${S}/src/log-replayer/ && cmake .
    cd ${S}/src/fuel-stop-advisor && cmake ${EXTRA_CMAKEFLAGS}
    cd ${S}/src/hmi/qml/hmi-launcher && cmake ${EXTRA_CMAKEFLAGS} -DQT_MOC=${STAGING_BINDIR_NATIVE}/qt5/moc
}

do_compile() {
    cd ${S}/src/genivilogreplayer && make
    cd ${S}/src/log-replayer && make
    cd ${S}/src/fuel-stop-advisor && make
    cd ${S}/src/hmi/qml/hmi-launcher && make
}

do_install() {
    install -d ${D}${bindir}/navigation-application/qml
    install -m 0755 ${S}/src/fuel-stop-advisor/fuel-stop-advisor ${D}${bindir}
    install -m 0755 ${S}/src/log-replayer/src/log-replayer ${D}${bindir}
    install -m 0755 ${S}/src/hmi/qml/hmi-launcher/hmi-launcher ${D}${bindir}
    install -m 0755 ${S}/src/hmi/qml/*.qml ${S}/src/hmi/qml/FSA.qmlproject ${D}/${bindir}/navigation-application/qml
    cp -r ${S}/src/hmi/qml/Core ${D}/${bindir}/navigation-application/qml

    install -d ${D}${libdir}/navigation
    install -m 0755 ${S}/src/genivilogreplayer/genivilogreplayerplugin.so ${D}${libdir}/navigation

    install -d ${D}${sysconfdir}/ambd/
    install -m 0755 ${WORKDIR}/logreplayerconfig ${D}${sysconfdir}/ambd/

    cp -r ${WORKDIR}/pregenerated_files/images ${WORKDIR}/pregenerated_files/style-sheets ${D}${bindir}/navigation-application/qml/Core
}

FILES_${PN} += "${libdir}/navigation/genivilogreplayerplugin.so"
FILES_${PN}-dbg += "${libdir}/navigation/.debug/"

INSANE_SKIP_${PN} = "dev-so"
