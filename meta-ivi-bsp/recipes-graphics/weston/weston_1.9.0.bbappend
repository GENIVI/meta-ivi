PR = "r2"

PKG_MESA_qemux86 = "mesa-megadriver "
PKG_MESA_qemux86-64 = "mesa-megadriver "
PKG_MESA_vexpressa9 = "mesa-megadriver "
PKG_MESA ?= " "

RDEPENDS_${PN} = "${PKG_MESA} \
    "
