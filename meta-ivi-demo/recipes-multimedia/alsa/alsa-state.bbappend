FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = " file://asound.conf"

SRC_URI_append_koelsch = " file://asound.state"
