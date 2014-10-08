FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = " file://0001-Porting-Pulse-Routing-Interface-from-AM-v1.x-to-AM-v.patch \
                   file://0001-Porting-Pulse-Control-Interface-from-AM-v1.x-to-AM-v.patch \
                 "
EXTRA_OECMAKE += "-DWITH_PULSE_ROUTING_PLUGIN=ON -DWITH_PULSE_CONTROL_PLUGIN=ON -DWITH_ENABLED_IPC=DBUS -DWITH_DATABASE_STORAGE=ON"

FILES_${PN} += "${libdir}/audioManager/control/* \
                ${libdir}/audioManager/routing/* \
               "
