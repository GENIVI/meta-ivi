LIB_DEPS := "${@oe_filter_out('mesa-dri', '${LIB_DEPS}', d)}"
LIB_DEPS += "virtual/libgl"

FILESEXTRAPATHS := "${THISDIR}/${PN}"
SRC_URI_append = " file://X.service \
                 "

# If we don't have mesa-dri as virtual/gl we deactivate this support in xserver and
# remove remove xorg-extension-glx from RDEPENDS as we user OPENGLES and EGL
python () {
    if ((d.getVar("PREFERRED_PROVIDER_virtual/libgl", True) or "").find("mesa-dri") == -1):
	    extraoeconf = d.getVar("EXTRA_OECONF", True)
	    extraoeconf += " --disable-glx --disable-dri"
	    d.setVar('EXTRA_OECONF', extraoeconf)
    else:
	    d.setVar('RDEPENDS_xserver-xorg', "xserver-xorg-extension-glx")
}

do_install_append() {
    install -d ${D}/${libdir}/systemd/system
    install -m 0755 ${WORKDIR}/X.service ${D}/${libdir}/systemd/system
}

FILES_${PN} += "${libdir}/systemd/system"

WARN_QA = "ldflags useless-rpaths rpaths staticdev"
