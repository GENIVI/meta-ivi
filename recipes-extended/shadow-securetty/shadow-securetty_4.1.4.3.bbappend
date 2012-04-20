FILESEXTRAPATHS := "${THISDIR}/${PN}"

SRC_URI_append_vexpressa9 = " file://securetty-vea9"

python do_add_ports () {
    if ((d.getVar("MACHINE", True) or "").find("vexpressa9") != -1):
		conf=d.getVar("WORKDIR", True)
		conf += "/securetty"
		aconf=d.getVar("WORKDIR", True)
		aconf += "/securetty-vea9"
		fin = open(aconf, "r")
		data = fin.read()
		fin.close()
		fout = open(conf, "a")
		fout.write(data)
		fout.close()
}

addtask do_add_ports after do_patch before do_configure