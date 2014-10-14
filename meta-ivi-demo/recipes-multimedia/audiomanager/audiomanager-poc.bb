SUMMARY = "GENIVI AudioManager PoC"
DESCRIPTION = "Proof of Concept of the Genivi AudioManager"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://AudioManagerPoC/README;md5=6825421ff1d3b214ec0bac87ae559c56"

DEPENDS = "qtbase qtdeclarative qtwebkit"

BRANCH = "Intreprid_stable_branch"
VERSION = "6.1"

SRC_URI = "git://git.projects.genivi.org/AudioManager.git;branch=${BRANCH};tag=${VERSION} \
           file://0001-AudioManager-Proof-of-Concept.patch \
          "

S = "${WORKDIR}/git"

PATCHTOOL = "git"

QMAKE_PROFILES = "${S}/AudioManagerPoC"

inherit qmake5

do_install_append() {
    mkdir -p ${D}/${bindir}/am-poc/qml
    mv ${D}/${bindir}/AudioManager_PoC ${D}/${bindir}/am-poc
    cp ${S}/AudioManagerPoC/*.sh ${D}/${bindir}/am-poc
    cp ${S}/AudioManagerPoC/*.qml ${D}/${bindir}/am-poc/qml/
}

FILES_${PN}-dbg += "/usr/bin/am-poc/.debug/*"
