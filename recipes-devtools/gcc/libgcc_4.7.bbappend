FILES_${PN} = "${libdir}/libgcc*.so.*"
FILES_${PN}-dev = " \
  ${libdir}/libgcc*.so \
  ${libdir}/${TARGET_SYS}/${BINV}/*crt* \
  ${libdir}/${TARGET_SYS}/${BINV}/libgcc*"

FILES_${PN}-dbg += "${libdir}/.debug/"

do_install_append () {
	# Move libgcc_s into /usr/lib
	mkdir -p ${D}${libdir}
	if [ -f ${D}${base_libdir}/libgcc_s.so ]; then
		mv ${D}${base_libdir}/libgcc* ${D}${libdir}
	else
		mv ${D}${base_libdir}/libgcc* ${D}${base_libdir} || true
	fi
	rmdir ${D}${base_libdir}
}
