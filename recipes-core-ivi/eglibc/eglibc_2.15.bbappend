# Use local $PN directory
FILESEXTRAPATHS := "${THISDIR}/${PN}"

# Add patch to avoid overwriting libc_cv_rootsbindir
SRC_URI += " file://dont-set-libc_cv_rootsbindir-if-set-already.patch \
           "

# Use paths from usr
export libc_cv_slibdir = "${libdir}"
export libc_cv_rootsbindir = "/usr/sbin"
base_libdir = "${libdir}"
base_prefix = "${prefix}"
base_sbindir = "${sbindir}"

# Reorder PACKAGES to have -dev at the end
PACKAGES = "${PN}-dbg ${PN} catchsegv${PKGSUFFIX} sln${PKGSUFFIX} nscd${PKGSUFFIX} ldd${PKGSUFFIX} ${PN}-mtrace ${PN}-utils eglibc-extra-nss${PKGSUFFIX} eglibc-thread-db${PKGSUFFIX} ${PN}-pic ${PN}-doc libcidn${PKGSUFFIX} libmemusage${PKGSUFFIX} libsegfault${PKGSUFFIX} ${PN}-pcprofile libsotruss${PKGSUFFIX} ${PN}-dev ${PN}-staticdev"

# New location of sln
FILES_sln${PKGSUFFIX} = "${sbindir}/sln"
