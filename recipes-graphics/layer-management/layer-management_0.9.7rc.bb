DESCRIPTION = "LayerManager"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "environment/base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=249d3578d6bba1bb946148d367a28080"

DEPENDS = "virtual/libgl dbus libxcomposite"

PR = "r1"

SRCREV = "d066997c719141d9ff816cde3e94e06bfd8c1a72"

SRC_URI = "git://git.genivi.org/srv/git/layer_management;protocol=git \
	   file://point-to-internal-CMakeVersions.patch \
	   file://include-unistd-for-usleep-in-OpenGLES2App.patch \
	   file://include-unistd-for-alarm-in-example.patch \
	   file://layermanager.service \
	   file://layermanager_demo.service \
          "
# Needed this for imx6 boards to use precompiled EGL libraries
python () {
    if ((d.getVar("MACHINE", True) or "").find("imx6sabre") != -1):
	flags = d.getVar("OECMAKE_CXX_FLAGS", True)
	flags += " -DLINUX"
	d.setVar('OECMAKE_CXX_FLAGS', flags)
}

S = "${WORKDIR}/git"

inherit autotools gettext cmake

do_install_append() {
    install -d ${D}/${libdir}/systemd/system
    install -m 0755 ${WORKDIR}/layermanager.service ${D}/${libdir}/systemd/system
    install -m 0755 ${WORKDIR}/layermanager_demo.service ${D}/${libdir}/systemd/system
}

FILES_${PN} += "${libdir}/lib* \
		${libdir}/layermanager/lib* \
		${libdir}/layermanager/communicator/lib* \
		${libdir}/layermanager/ipcmodules/lib* \
		${libdir}/layermanager/renderer/lib* \
		${libdir}/layermanager/renderer/renderer* \
		${libdir}/systemd"

FILES_${PN}-dev = "${includedir}/*"
FILES_${PN}-staticdev += "${libdir}/layermanager/static/lib*"
FILES_${PN}-dbg += "${libdir}/layermanager/.debug/ \
		    ${libdir}/layermanager/communicator/.debug/ \
		    ${libdir}/layermanager/ipcmodules/.debug/ \
		    ${libdir}/layermanager/renderer/.debug/ "

WARN_QA = "ldflags useless-rpaths rpaths staticdev"
