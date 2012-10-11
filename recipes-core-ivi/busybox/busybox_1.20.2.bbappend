PRINC = "3"

# Do't register init scripts
INITSCRIPT_PACKAGES = ""

INITSCRIPT_NAME_${PN}-httpd = ""
INITSCRIPT_NAME_${PN}-syslog = ""
INITSCRIPT_NAME_${PN}-mdev = ""
INITSCRIPT_PARAMS_${PN}-mdev = ""
INITSCRIPT_NAME_${PN}-udhcpd = ""
INITSCRIPT_NAME_${PN}-udhcpc = ""

# Don' install syslog and udhcpc
RRECOMMENDS_${PN} = ""
