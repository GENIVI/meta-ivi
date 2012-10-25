PRINC = "1"

# xz is GPLv3
# Remove this package from depends and configure
DEPENDS := "${@oe_filter_out('xz', '${DEPENDS}', d)}"
EXTRA_OECONF := "${@oe_filter_out('--enable-xz', '${EXTRA_OECONF}', d)}"
