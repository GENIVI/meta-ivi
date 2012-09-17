# Use local $PN directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

# Add patch to avoid overwriting libc_cv_rootsbindir
SRC_URI += " file://dont-set-libc_cv_rootsbindir-if-set-already.patch \
             file://eglibc_2.16_add_af_bus_support.patch \
           "

# Use paths from usr
export libc_cv_slibdir = "${libdir}"
export libc_cv_rootsbindir = "/usr/sbin"
base_libdir = "${libdir}"
base_prefix = "${prefix}"
base_sbindir = "${sbindir}"
sysconfdir = "/etc"

# Reorder PACKAGES to have -dev at the end
PACKAGES = "${PN}-dbg ${PN} catchsegv sln nscd ldd ${PN}-mtrace ${PN}-utils eglibc-extra-nss eglibc-thread-db ${PN}-pic ${PN}-doc libcidn libmemusage libsegfault ${PN}-pcprofile libsotruss ${PN}-dev ${PN}-staticdev"

# New location of sln
FILES_sln$ = "${sbindir}/sln"
