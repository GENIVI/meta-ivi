SUMMARY = "GENIVI AudioManager PoC"
DESCRIPTION = "Proof of Concept of the Genivi AudioManager"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://AudioManagerPoC/README;md5=033158825134ef0607a0bd03ad3a66c3"

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

FILES_${PN} += "/opt/audiomanager-poc/*"
FILES_${PN}-dbg += "/usr/bin/am-poc/.debug/*"
