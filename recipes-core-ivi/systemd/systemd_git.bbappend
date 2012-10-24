PRINC = "1"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# xz is GPLv3
# Remove this package from depends and configure
DEPENDS := "${@oe_filter_out('xz', '${DEPENDS}', d)}"
EXTRA_OECONF := "${@oe_filter_out('--enable-xz', '${EXTRA_OECONF}', d)}"

SRC_URI_append = " \
                   file://don-t-compress-without-xz.patch \
                   file://fix-without-xz-build.patch \
                   "