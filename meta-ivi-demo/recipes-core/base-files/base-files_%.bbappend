FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = " file://add_LD_PRELOAD.patch"
