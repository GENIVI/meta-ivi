SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = \
    "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

BASE_URL = "http://docs.projects.genivi.org/yamaica-update-site/CommonAPI"
BASE_VER = "${@'.'.join(d.getVar('PV', True).split('.')[0:2])}"

def get_launcher_name(d):
    BS = d.getVar('BUILD_SYS', True)
    if BS == "x86_64-linux":
        launcherName = "${LAUNCHER_BASE}-linux-x86_64"
    elif BS == "i686-linux":
        launcherName = "${LAUNCHER_BASE}-linux-x86"
    else:
        bb.fatal("Build system '%s' is not supported by ${PN}_${PV} recipe" % BS)
    return launcherName

LAUNCHER = "${@get_launcher_name(d)}"

inherit native
SANITY_REQUIRED_UTILITIES += "java"

S = "${WORKDIR}"
DD = "${D}${datadir}/${PN}-${PV}"

do_install() {
    install -d ${DD}
    install -m 0644 ${S}/artifacts.xml ${DD}
    install -m 0755 ${S}/${LAUNCHER_BASE}-linux-x86 ${DD}
    install -m 0644 ${S}/${LAUNCHER_BASE}-linux-x86.ini ${DD}
    install -m 0755 ${S}/${LAUNCHER_BASE}-linux-x86_64 ${DD}
    install -m 0644 ${S}/${LAUNCHER_BASE}-linux-x86_64.ini ${DD}
    for dir in ./configuration ./features ./plugins; do
        for item in $(find ${dir} -name '*' -print); do
            if [ -d ${item} ]; then
                install -d ${DD}/${item}
            else
                install -m 0644 ${S}/${item} ${DD}/$(dirname ${item})
            fi
        done
    done
    install -d "${D}${bindir}"
    ln -sf -T "${DD}/${LAUNCHER}" ${D}${bindir}/${LAUNCHER_LINK}
}
