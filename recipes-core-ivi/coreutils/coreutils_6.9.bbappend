# [ gets a special treatment and is not included in this
bindir_progs = "base64 basename cksum comm csplit cut dir dircolors dirname du \
                env expand expr factor fmt fold groups head hostid id install \
                join link logname md5sum mkfifo nice nl nohup od paste pathchk \
                pinky pr printenv printf ptx readlink seq sha1sum sha224sum sha256sum \
                sha384sum sha512sum shred shuf sort split stat sum tac tail tee test \
                tr tsort tty unexpand uniq unlink users vdir wc who whoami yes uptime"

# hostname gets a special treatment and is not included in this
bindir_progs += " cat chgrp chmod chown cp date dd echo false kill ln ls mkdir \
                     mknod mv pwd rm rmdir sleep stty sync touch true uname hostname "

base_bindir_progs = ""

sbindir_progs= "chroot"

do_install_append () {
	rmdir ${D}/${base_bindir}
}

ALTERNATIVE_${PN} = "lbracket ${bindir_progs} ${base_bindir_progs} ${sbindir_progs}"

