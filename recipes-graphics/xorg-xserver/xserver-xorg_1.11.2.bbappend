PRINC := "${@int(PRINC) + 3}"

LIB_DEPS += "virtual/libgl"

FILESEXTRAPATHS := "${THISDIR}/${PN}"

inherit systemd
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "X.service"
SYSTEMD_AUTO_ENABLE = "disable"

SRC_URI_append = " file://X.service \
                 "

# If we don't have mesa-dri as virtual/gl we deactivate this support in xserver and
# remove remove xorg-extension-glx from RDEPENDS as we use OPENGLES and EGL
python () {
    if ((d.getVar("PREFERRED_PROVIDER_virtual/libgl", True) or "").find("mesa-dri") == -1):
	    extraoeconf = d.getVar("EXTRA_OECONF", True)
	    extraoeconf += " --disable-glx --disable-dri"
	    d.setVar('EXTRA_OECONF', extraoeconf)
    else:
	    d.setVar('RDEPENDS_xserver-xorg', "xserver-xorg-extension-glx")
}
