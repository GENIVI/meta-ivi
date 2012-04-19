DESCRIPTION = "LayerManager"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "environment/base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=249d3578d6bba1bb946148d367a28080"

DEPENDS = "virtual/libgl dbus libxcomposite"

SRCREV = "02c4d61f14d8d684757f5d13bf2105ddb11ac8ee"

SRC_URI = "git://git.genivi.org/srv/git/layer_management;protocol=git \
	   file://point-to-internal-CMakeVersions.patch \
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

FILES_${PN} += "${libdir}/lib* \
		${libdir}/layermanager/lib* \
		${libdir}/layermanager/communicator/lib* \
		${libdir}/layermanager/renderer/lib* \
		${libdir}/layermanager/renderer/renderer*"

FILES_${PN}-dev += "${includedir}/*"
FILES_${PN}-staticdev += "${libdir}/layermanager/static/lib*"
FILES_${PN}-dbg += "${libdir}/layermanager/.debug/ \
		    ${libdir}/layermanager/communicator/.debug/ \
		    ${libdir}/layermanager/renderer/.debug/ "
