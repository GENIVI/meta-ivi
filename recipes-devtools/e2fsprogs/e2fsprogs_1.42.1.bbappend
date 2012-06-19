EXTRA_OECONF := "${@oe_filter_out('--libdir=${base_libdir}', '${EXTRA_OECONF}', d)}"
EXTRA_OECONF := "${@oe_filter_out('--libdir=${base_libdir}', '${EXTRA_OECONF}', d)}"

EXTRA_OECONF += "--libdir=${libdir} --sbindir=${sbindir}"

base_libdir = "${libdir}"
base_sbindir = "${sbindir}"
base_prefix = "${prefix}"

