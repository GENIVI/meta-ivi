SUMMARY = "testsuite for CommonAPI"
SECTION = "test"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

inherit cmake pkgconfig

DEPENDS += " common-api-c++ common-api-c++-dbus glib-2.0"

SRC_URI = " \
    file://${PN}-${PV}.tar.gz \
    file://${BPN}_t.inc \
    "

S = "${WORKDIR}/${PN}"

EXTRA_OECMAKE += "-DUSE_INSTALLED_COMMONAPI=ON -DUSE_INSTALLED_DBUS=ON -DCMAKE_INSTALL_PREFIX=/usr"

FILES_${PN} = " \
    /opt/tests/${PN}/E0* \
    /opt/tests/${PN}/*/README \
    /opt/tests/${PN}/*/commonapi4dbus.ini \
    /opt/tests/${PN}/*/*Client \
    /opt/tests/${PN}/*/*Service \
    /opt/tests/${PN}/${BPN}_t.inc \
    ${libdir}/*.so \
    "

FILES_${PN}-dev = ""

FILES_${PN}-dbg += " \
    /opt/tests/${PN}/.debug/* \
    /opt/tests/${PN}/*/.debug/* \
    ${libdir}/.debug/* \
    "

do_install() {
    _DEST=${D}/opt/tests/${PN}
    _BIN=${WORKDIR}/build

    install -d ${_DEST}
    install -d ${D}${libdir}
    for i in `\ls ${_BIN} | grep E0`; do
      install -m 0755 ${_BIN}/${i}/${i}Client  ${_DEST}
      install -m 0755 ${_BIN}/${i}/${i}Service ${_DEST}
      install -m 0755 ${_BIN}/${i}/lib${i}-dbus.so ${D}${libdir}
    done

    for i in BlueZ ObjectPathSimple UDisks2 VariantSimple; do
      install -d ${_DEST}/${i}
      install -m 0755 ${_BIN}/${i}/${i}Client    ${_DEST}/${i}
      rm -rf ${_DEST}/${i}/.debug
      if [ -e ${_BIN}/${i}/${i}Service ]; then
        install -m 0755 ${_BIN}/${i}/${i}Service ${_DEST}/${i}
      fi
      if [ -e ${S}/${i}/README ]; then
        install -m 0644 ${S}/${i}/README ${_DEST}/${i}
      fi
      if [ -e ${S}/${i}/commonapi4dbus.ini ]; then
        install -m 0644 ${S}/${i}/commonapi4dbus.ini ${_DEST}/${i}
      fi
      install -m 0755 ${_BIN}/${i}/lib${i}-dbus.so ${D}${libdir}
    done

   install -m 0755 ${WORKDIR}/${BPN}_t.inc ${D}/opt/tests/${PN}
}
