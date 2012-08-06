LICENSE = "GPLv2"

# http://www.freedesktop.org/wiki/Software/systemd/TheCaseForTheUsrMerge
# /bin → /usr/bin
# /sbin → /usr/sbin
# /lib → /usr/lib
# /lib64 → /usr/lib64

usr_merge() {
	#
	# TO DO: Edit kernel modules to be installed in usr lib and
	#        remove the workaround below
	#
	echo "usr-merge: moving modules in /usr/lib ..."
	mkdir -p $INSTALL_ROOTFS_RPM/usr/lib
	# was:
	# mv $INSTALL_ROOTFS_RPM/lib/modules $INSTALL_ROOTFS_RPM/usr/lib
	# rmdir $INSTALL_ROOTFS_RPM/lib/
	cp -x --no-dereference --preserve=link,mode,ownership,timestamps --strip-trailing-slashes --recursive $INSTALL_ROOTFS_RPM/lib -t $INSTALL_ROOTFS_RPM/usr/
	rm -rf $INSTALL_ROOTFS_RPM/lib/
	mv $INSTALL_ROOTFS_RPM/sbin/ldconfig $INSTALL_ROOTFS_RPM/usr/sbin/
	rmdir $INSTALL_ROOTFS_RPM/sbin

	echo "usr-merge: Creating symlinks for /usr merge stage ..."
	if [ ! -d $INSTALL_ROOTFS_RPM/sbin -a -d $INSTALL_ROOTFS_RPM/usr/sbin ]; then
		echo "usr-merge: Create simlink /sbin -> /usr/sbin ..."
		ln -sf ./usr/sbin $INSTALL_ROOTFS_RPM/sbin
	else
		echo "usr-merge: /sbin is already created. Can't create symlink for usr merge"
		exit 1
	fi

	if [ ! -d $INSTALL_ROOTFS_RPM/bin -a -d $INSTALL_ROOTFS_RPM/usr/bin ]; then
		echo "usr-merge: Create simlink /bin -> /usr/bin ..."
		ln -sf ./usr/bin $INSTALL_ROOTFS_RPM/bin
	else
		echo "usr-merge: /bin is already created. Can't create symlink for usr merge"
		exit 1
	fi

	if [ ! -d $INSTALL_ROOTFS_RPM/lib -a -d $INSTALL_ROOTFS_RPM/usr/lib ]; then
		echo "usr-merge: Create simlink /lib -> /usr/lib ..."
		ln -sf ./usr/lib $INSTALL_ROOTFS_RPM/lib
	else
		echo "usr-merge: /lib is already created. Can't create symlink for usr merge"
		exit 1
	fi

	#
	# /lib64 is optional
	#
	if [ ! -d $INSTALL_ROOTFS_RPM/lib64 -a -d $INSTALL_ROOTFS_RPM/usr/lib64 ]; then
		echo "usr-merge: Create simlink /lib64 -> /usr/lib64 ..."
		ln -sf ./usr/lib64 $INSTALL_ROOTFS_RPM/lib64
	fi
}
