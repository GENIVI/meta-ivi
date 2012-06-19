FILESEXTRAPATHS := "${THISDIR}/${PN}"

volatiles = ""

dirs1777 = "/tmp"
dirs755 = "/bin /boot /dev ${sysconfdir} ${sysconfdir}/default \
           ${sysconfdir}/skel /lib /mnt /proc /home/root /sbin \
           ${prefix} ${bindir} ${docdir} /usr/games ${includedir} \
           ${libdir} ${sbindir} ${datadir} \
           ${datadir}/common-licenses ${datadir}/dict ${infodir} \
           ${mandir} ${datadir}/misc ${localstatedir} \
           ${localstatedir}/backups ${localstatedir}/lib \
           /sys ${localstatedir}/lib/misc ${localstatedir}/spool \
           /run/cache /run/lock/subsys /run/log \
           /mnt /media /media/card /media/cf /media/net /media/ram \
           /media/union /media/realroot /media/hdd \
           /media/mmc1"

do_install () {
	for d in ${dirs755}; do
		install -m 0755 -d ${D}$d
	done
	for d in ${dirs1777}; do
		install -m 1777 -d ${D}$d
	done
	for d in ${dirs2775}; do
		install -m 2755 -d ${D}$d
	done
	for d in ${volatiles}; do
		ln -sf volatile/$d ${D}/${localstatedir}/$d
	done
	ln -sf ../run/cache ${D}/${localstatedir}/cache
	ln -sf ../run/lock ${D}/${localstatedir}/lock
	ln -sf ../run/log ${D}/${localstatedir}/log
	ln -sf ../run ${D}/${localstatedir}/run
	ln -sf ../tmp ${D}/${localstatedir}/tmp
	for d in card cf net ram; do
		ln -sf /media/$d ${D}/mnt/$d
	done

	${BASEFILESISSUEINSTALL}

	rotation=`cat ${WORKDIR}/rotation`
	if [ "$rotation" != "0" ]; then
		install -m 0644 ${WORKDIR}/rotation ${D}${sysconfdir}/rotation
	fi

	install -m 0644 ${WORKDIR}/fstab ${D}${sysconfdir}/fstab
	install -m 0644 ${WORKDIR}/filesystems ${D}${sysconfdir}/filesystems
	install -m 0644 ${WORKDIR}/usbd ${D}${sysconfdir}/default/usbd
	install -m 0644 ${WORKDIR}/profile ${D}${sysconfdir}/profile
	install -m 0644 ${WORKDIR}/shells ${D}${sysconfdir}/shells
	install -m 0755 ${WORKDIR}/share/dot.profile ${D}${sysconfdir}/skel/.profile
	install -m 0755 ${WORKDIR}/share/dot.bashrc ${D}${sysconfdir}/skel/.bashrc
	install -m 0644 ${WORKDIR}/inputrc ${D}${sysconfdir}/inputrc
	install -m 0644 ${WORKDIR}/nsswitch.conf ${D}${sysconfdir}/nsswitch.conf
	install -m 0644 ${WORKDIR}/host.conf ${D}${sysconfdir}/host.conf
	install -m 0644 ${WORKDIR}/motd ${D}${sysconfdir}/motd

	ln -sf /proc/mounts ${D}${sysconfdir}/mtab
}

# In usr merge case we don't need /sbin, /bin and /lib as these will be symlinks
do_install_append () {
	rmdir ${D}/bin
	rmdir ${D}/sbin
	rmdir ${D}/lib
}
