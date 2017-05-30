SUMMARY = "Genivi AudioManager Plugins"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "multimedia"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r2"

DEPENDS = "audiomanager capicxx-core-native capicxx-dbus-native libxml2"
RDEPENDS_${PN} += "libxml2"

SRCREV = "cb5797de3df41f4661c3055b0ea1a3e677c293aa"
SRC_URI = " \
    git://github.com/GENIVI/AudioManagerPlugins.git;branch=master;protocol=https \
    file://AM-Genivi-Filtering-out-JDK-warnings-in-CAPI-script.patch \
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
    capi_core_home=$(dirname `find ${WORKDIR}/recipe-sysroot-native -name commonapi-generator-linux-x86`)
    capi_dbus_home=$(dirname `find ${WORKDIR}/recipe-sysroot-native -name commonapi-dbus-generator-linux-x86`)
    if [ -L /usr/bin/java ]; then
       java_bin=$(readlink -f /usr/bin/java)
       if [ -L $java_bin ]; then
          J_HOME=$(readlink -f $java_bin | sed "s:/bin/java::")
       else
          J_HOME=$(readlink -f /usr/bin/java | sed "s:/bin/java::")
       fi
       rm -f ${capi_core_home}/jre ${capi_dbus_home}/jre
       ln -s ${J_HOME} ${capi_core_home}/
       ln -s ${J_HOME} ${capi_dbus_home}/
    else
       echo "hello. it's impossible"
    fi

    perl -pi -e 's|include\(CMakeDependentOption\)|include\(CMakeDependentOption\)\ninclude_directories\(${PKG_CONFIG_SYSROOT_DIR}/usr/include/audiomanager/AudioManagerUtilities\)|' ${S}/CMakeLists.txt
    perl -pi -e 's|include\(CMakeDependentOption\)|include\(CMakeDependentOption\)\ninclude_directories\(${PKG_CONFIG_SYSROOT_DIR}/usr/include/audiomanager/AudioManagerCore\)|' ${S}/CMakeLists.txt
    perl -pi -e 's|include\(CMakeDependentOption\)|include\(CMakeDependentOption\)\ninclude_directories\(${PKG_CONFIG_SYSROOT_DIR}/usr/include/audiomanager\)|' ${S}/CMakeLists.txt

    perl -pi -e 's|\${CMAKE_INSTALL_PREFIX}/etc/controllerconf|/etc/controllerconf|' ${S}/PluginControlInterfaceGeneric/CMakeLists.txt

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
