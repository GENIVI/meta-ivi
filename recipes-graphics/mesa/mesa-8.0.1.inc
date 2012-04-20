DEPENDS += "mesa-dri-glsl-native"


SRC_URI = "ftp://ftp.freedesktop.org/pub/mesa/${PV}/MesaLib-${PV}.tar.bz2 \ 
           file://crossfix.patch \
           file://crossfix-mklib.patch \
           file://mesa_fix_for_x32.patch \
           file://remove_OES_EGL_image_externals.patch \
          "

S = "${WORKDIR}/Mesa-${PV}"

SRC_URI[md5sum] = "24eeebf66971809d8f40775a379b36c9"
SRC_URI[sha256sum] = "593af02ea2b5b7afb057a12c550f2c03b1d1cf59a716972d601c1499545ea0a7"

do_configure_prepend() {
  #check for python not python2, because python-native does not stage python2 binary/link
  sed -i 's/AC_CHECK_PROGS(\[PYTHON2\], \[python2 python\])/AC_CHECK_PROGS(\[PYTHON2\], \[python python\])/g' ${S}/configure.ac
  # We need builtin_compiler built for buildhost arch instead of target (is provided by mesa-dri-glsl-native)"
  sed -i "s#\./builtin_compiler#${STAGING_BINDIR_NATIVE}/glsl/builtin_compiler#g" ${S}/src/glsl/Makefile
}
