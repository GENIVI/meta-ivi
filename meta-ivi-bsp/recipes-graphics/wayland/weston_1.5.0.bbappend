RDEPENDS_${PN}_append = " libegl-gallium llvm3.3 "

EXTRA_OECONF_append_vexpressa9 = "\
    --enable-simple-egl-clients  \
    WESTON_NATIVE_BACKEND=fbdev-backend.so \
    "
EXTRA_OECONF_append_qemux86 = "\
    --enable-simple-egl-clients  \
    WESTON_NATIVE_BACKEND=fbdev-backend.so \
    "
EXTRA_OECONF_append_qemux86-64 = "\
    --enable-simple-egl-clients  \
    WESTON_NATIVE_BACKEND=fbdev-backend.so \
    "
