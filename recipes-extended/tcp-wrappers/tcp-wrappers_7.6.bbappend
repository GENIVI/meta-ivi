# Install and package libwrap in ${libdir}
FILES_libwrap = "${libdir}/lib*${SOLIBS}"

do_install () {
	oe_libinstall -a libwrap ${D}${libdir}
	oe_libinstall -C shared -so libwrap ${D}${libdir}

	#rel_lib_prefix=`echo ${libdir} | sed 's,\(^/\|\)[^/][^/]*,..,g'`
	#libname=`readlink ${D}${base_libdir}/libwrap.so | xargs basename`
	#ln -s ${rel_lib_prefix}${base_libdir}/${libname} ${D}${libdir}/libwrap.so
	#rm -f ${D}${base_libdir}/libwrap.so

	install -d ${D}${sbindir}
	for b in ${BINS}; do
		install -m 0755 $b ${D}${sbindir}/ || exit 1
	done

	install -d ${D}${mandir}/man3
	for m in ${MANS3}; do
		install -m 0644 $m.3 ${D}${mandir}/man3/ || exit 1
	done

	install -d ${D}${mandir}/man5
	for m in ${MANS5}; do
		install -m 0644 $m.5 ${D}${mandir}/man5/ || exit 1
	done

	install -d ${D}${mandir}/man8
	for m in ${MANS8}; do
		install -m 0644 $m.8 ${D}${mandir}/man8/ || exit 1
	done

	install -m 0644 ${WORKDIR}/try-from.8 ${D}${mandir}/man8/
	install -m 0644 ${WORKDIR}/safe_finger.8 ${D}${mandir}/man8/

	install -d ${D}${includedir}
	install -m 0644 tcpd.h ${D}${includedir}/
}
