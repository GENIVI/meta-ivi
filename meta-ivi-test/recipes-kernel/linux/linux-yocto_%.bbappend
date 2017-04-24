FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append_intel-corei7-64 = " file://hidmultitouch.cfg"

# skip
do_kernel_version_sanity_check() {
}
