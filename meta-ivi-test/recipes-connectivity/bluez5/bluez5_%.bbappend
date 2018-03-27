#
# for test
#
PACKAGECONFIG += " readline testing tools"

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   mv ${D}/${libdir}/bluez/test/* ${D}/opt/tests/${PN}
   rmdir ${D}/${libdir}/bluez/test
   rmdir ${D}/${libdir}/bluez

   # move desired tools that upstream leaves in build area
   for f in ${NOINST_TOOLS} ; do
      g=`basename $f`
      mv ${D}/${bindir}/$g ${D}/opt/tests/${PN}
   done
}

def get_noinst_tools_paths2 (d, bb, tools):
    s = list()
    bindir = '/opt/tests/' + d.getVar("PN", True)
    for bdp in tools.split():
        f = os.path.basename(bdp)
        s.append("%s/%s" % (bindir, f))
    return "\n".join(s)

FILES_${PN}-noinst-tools = "${@get_noinst_tools_paths2(d, bb, d.getVar('NOINST_TOOLS', True))}"

PACKAGES += "${PN}-test"
DEPENDS_${PN}-test = "${PN}"

FILES_${PN}-dbg += " \
   /opt/tests/${PN}/.debug/* \
   /opt/tests/${PN}/lib/.debug/* \
   "
FILES_${PN}-test = " \
   /opt/tests/${PN}/* \
   "
