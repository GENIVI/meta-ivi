FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# Set "root" as password for user "root"
SRC_URI += " \
    file://add-default-root-pass.patch \
    "
