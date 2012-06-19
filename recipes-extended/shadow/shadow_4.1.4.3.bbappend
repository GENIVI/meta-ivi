do_install_append() {
	# Move binaries to the locations we want
	mv ${D}${base_sbindir}/* ${D}${sbindir}/
	mv ${D}${base_bindir}/* ${D}${bindir}/
	rmdir ${D}${base_sbindir}/
	rmdir ${D}${base_bindir}/
}

bindir_progs = "passwd chfn newgrp chsh groups login"
sbindir_progs = "chpasswd vipw vigr"
base_bindir_progs = ""
base_sbindir_progs = ""
