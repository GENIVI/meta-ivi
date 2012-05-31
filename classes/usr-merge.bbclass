LICENSE = "GPLv2"

# http://www.freedesktop.org/wiki/Software/systemd/TheCaseForTheUsrMerge
# /bin → /usr/bin
# /sbin → /usr/sbin
# /lib → /usr/lib
# /lib64 → /usr/lib64

usr_merge() {
	echo /usr merge stage ...
	if [ -d $INSTALL_ROOTFS_RPM/sbin -a -d $INSTALL_ROOTFS_RPM/usr/sbin ]; then
		echo "Moving /sbin and /usr/sbin ..."
		cp -r $INSTALL_ROOTFS_RPM/sbin/* $INSTALL_ROOTFS_RPM/usr/sbin/
		rm -rf $INSTALL_ROOTFS_RPM/sbin
		ln -sf ./usr/sbin $INSTALL_ROOTFS_RPM/sbin
	fi
	if [ -d $INSTALL_ROOTFS_RPM/bin -a -d $INSTALL_ROOTFS_RPM/usr/bin ]; then
		echo "Moving /bin and /usr/bin ..."
		cp -r $INSTALL_ROOTFS_RPM/bin/* $INSTALL_ROOTFS_RPM/usr/bin/
		rm -rf $INSTALL_ROOTFS_RPM/bin
		ln -sf ./usr/bin $INSTALL_ROOTFS_RPM/bin
	fi
	if [ -d $INSTALL_ROOTFS_RPM/lib -a -d $INSTALL_ROOTFS_RPM/usr/lib ]; then
		echo "Moving /lib and /usr/lib ..."
		cp -r $INSTALL_ROOTFS_RPM/lib/* $INSTALL_ROOTFS_RPM/usr/lib/
		rm -rf $INSTALL_ROOTFS_RPM/lib
		ln -sf ./usr/lib $INSTALL_ROOTFS_RPM/lib
	fi
	if [ -d $INSTALL_ROOTFS_RPM/lib64 -a -d $INSTALL_ROOTFS_RPM/usr/lib64 ]; then
		echo "Moving /lib64 and /usr/lib64 ..."
		cp -r $INSTALL_ROOTFS_RPM/lib64/* $INSTALL_ROOTFS_RPM/usr/lib64/
		rm -rf $INSTALL_ROOTFS_RPM/lib64
		ln -sf ./usr/lib64 $INSTALL_ROOTFS_RPM/lib64
	fi
}

