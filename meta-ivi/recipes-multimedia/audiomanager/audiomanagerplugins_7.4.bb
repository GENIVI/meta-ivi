SUMMARY = "Genivi AudioManager Plugins"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "multimedia"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "audiomanager capicxx-core-native capicxx-dbus-native"
RDEPENDS_${PN} += "libxml2"

SRCREV = "8096eee2649585d13464b23e9ab940f0e030c56a"
SRC_URI = " \
    git://git.projects.genivi.org/AudioManagerPlugins.git;branch=master;protocol=http \
    "
S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = " \
    -DWITH_COMMAND_INTERFACE_CAPI=ON -DWITH_COMMAND_INTERFACE_DBUS=ON \
    -DWITH_ROUTING_INTERFACE_CAPI=ON -DWITH_ROUTING_INTERFACE_DBUS=ON \
    -DWITH_ROUTING_INTERFACE_ASYNC=ON \
    -DWITH_GENERIC_CONTROLLER=ON \
    "

do_configure_prepend() {
    perl -pi -e 's|include\(CMakeDependentOption\)|include\(CMakeDependentOption\)\ninclude_directories\(${PKG_CONFIG_SYSROOT_DIR}/usr/include/audiomanager/AudioManagerUtilities\)|' ${S}/CMakeLists.txt
    perl -pi -e 's|include\(CMakeDependentOption\)|include\(CMakeDependentOption\)\ninclude_directories\(${PKG_CONFIG_SYSROOT_DIR}/usr/include/audiomanager/AudioManagerCore\)|' ${S}/CMakeLists.txt
    perl -pi -e 's|include\(CMakeDependentOption\)|include\(CMakeDependentOption\)\ninclude_directories\(${PKG_CONFIG_SYSROOT_DIR}/usr/include/audiomanager\)|' ${S}/CMakeLists.txt

    perl -pi -e 's|set\(LIBRARY_OUTPUT_PATH \${DEFAULT_PLUGIN_ROUTING_DIR}\)|set\(LIBRARY_OUTPUT_PATH ${B}\)|' ${S}/PluginRouting*/CMakeLists.txt
    perl -pi -e 's|COMMAND find "/usr/local/share/.*"|COMMAND find "\${PKG_CONFIG_SYSROOT_DIR}/../"|' ${S}/PluginRoutingInterfaceCAPI/cmake/CommonAPI.cmake
    perl -pi -e 's|COMMAND find "/usr/local/share/.*"|COMMAND find "\${PKG_CONFIG_SYSROOT_DIR}/../"|' ${S}/PluginCommandInterfaceCAPI/cmake/CommonAPI.cmake
}

do_configure_append() {
    perl -pi -e 's|/\* #undef LIBRARY_OUTPUT_PATH \*/|#define LIBRARY_OUTPUT_PATH "/usr/lib/command"|' ${S}/PluginCommandInterfaceDbus/include/configCommandDbus.h
}

FILES_${PN} += " \
    ${libdir}/* \
    /usr/share/* \
    "

FILES_${PN}-dev = " \
    /usr/include/ \
    ${libdir}/pkgconfig/ \
    "

do_install_append() {
    mv ${D}${libdir}/audiomanager/routing/* ${D}${libdir}/routing/
    rmdir ${D}${libdir}/audiomanager/routing
    rmdir ${D}${libdir}/audiomanager
}