SUMMARY = "GENIVI AudioManager PoC"
DESCRIPTION = "Proof of Concept of the Genivi AudioManager"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://AudioManagerPoC/README;md5=a14727a1c077b5b52e8574824a2f9458"

DEPENDS = "qtbase qtdeclarative"

BRANCH = "Intreprid_stable_branch"
VERSION = "6.1"

SRC_URI = "git://git.projects.genivi.org/AudioManager.git;branch=${BRANCH};tag=${VERSION} \
           file://0001-First-draft-for-Am-PoC.patch \
          "

S = "${WORKDIR}/git"

PATCHTOOL = "git"

QMAKE_PROFILES = "${S}/AudioManagerPoC"

inherit qmake5

do_install_append() {
    mkdir -p ${D}/${bindir}/am-poc/qml
    mv ${D}/${bindir}/falcon_hmi ${D}/${bindir}/am-poc
    cp ${S}/AudioManagerPoC/*.qml ${D}/${bindir}/am-poc/qml/
}

FILES_${PN}-dbg += "/usr/bin/am-poc/.debug/*"
