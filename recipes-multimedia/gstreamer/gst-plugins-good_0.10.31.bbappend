PRINC := "${@int(PRINC) + 1}"

# Remove gconf and soup from dependencies and deactivate these in configure
DEPENDS := "${@oe_filter_out('libsoup-2.4','${DEPENDS}',d)}"
DEPENDS := "${@oe_filter_out('gconf','${DEPENDS}',d)}"
EXTRA_OECONF += "--disable-gconf --disable-soup"
