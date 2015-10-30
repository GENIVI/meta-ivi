PR = "r1"

#
# please, read mesa/READM.gallium about an issue when using gallium
#
PACKAGES =+ " \
    libegl-gallium libgbm-gallium \
    "

FILESEXTRAPATHS_append := "${THISDIR}/${PN}"
SRC_URI_append = " \
    file://revert-c46c551c56f78c6bf9e63524c89478695fc4f525.patch \
    "
SRC_URI_append_qemux86 = " \
    file://revert-363b53f00069af718f64cf047f19ad5681a8bf6d.patch \
    "
SRC_URI_append_qemux86-64 = " \
    file://revert-363b53f00069af718f64cf047f19ad5681a8bf6d.patch \
    "

PACKAGECONFIG[gallium-egl]  = "--enable-gallium-egl, --disable-gallium-egl"

FILES_libegl-gallium = "${libdir}/egl/egl_gallium.so*"
FILES_libgbm-gallium = "${libdir}/gbm/gbm_gallium_drm.so*"

PACKAGECONFIG_append_qemux86 = " gallium gallium-egl gallium-llvm"
PACKAGECONFIG_append_qemux86-64 = " gallium gallium-egl gallium-llvm"
