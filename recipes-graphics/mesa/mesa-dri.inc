PROTO_DEPS += "dri2proto"
LIB_DEPS += "libdrm expat"

# most of our targets do not have DRI so will use mesa-xlib
DEFAULT_PREFERENCE = "-1"

DRIDRIVERS = "swrast"
DRIDRIVERS_append_x86 = ",i915,i965"
DRIDRIVERS_append_x86-64 = ",i915,i965"

EXTRA_OECONF += "--disable-gallium --without-gallium-drivers --with-dri-drivers=${DRIDRIVERS} --enable-gles2 --enable-glx --enable-dri --disable-osmesa --with-egl-platform=drm --disable-xlib-glx"

python populate_packages_prepend() {
    import os.path

    dri_drivers_root = os.path.join(d.getVar('libdir', 1), "dri")

    do_split_packages(d, dri_drivers_root, '^(.*)_dri\.so$', 'mesa-dri-driver-%s', 'Mesa %s DRI driver', extra_depends='')
}

PACKAGES_DYNAMIC = "mesa-dri-driver-*"

FILES_${PN}-dbg += "${libdir}/dri/.debug/*"

FILES_${PN}-driver-swrast += "${libdir}/dri/libdricore.so"
FILES_${PN}-driver-swrast += "${libdir}/dri/libglsl.so"
