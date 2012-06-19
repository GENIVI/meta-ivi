EXTRA_OEMAKE = "PKG_LIB_DIR=${libdir} PKG_DEVLIB_DIR=${libdir}"

FILES_lib${BPN} = "${libdir}/lib*${SOLIBS}"

FILES_lib${BPN}-dev = "${includedir} \
                       ${libdir}/lib*${SOLIBSDEV} \
                       ${libdir}/lib*.la \
                       ${libdir}/lib*${SOLIBSDEV} \
                       ${libdir}/lib*.la"

FILES_lib${BPN}-staticdev = "${libdir}/*.a ${libdir}/*.a"

# We don't need to fix symlinks after moving to libdir
fix_symlink () {
	if test "${libdir}" = "${base_libdir}" ; then
		return
	fi
}
