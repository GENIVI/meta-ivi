SUMMARY = "Common API C++ D-Bus generator"

LAUNCHER_BASE = "commonapi-dbus-generator"
LAUNCHER_LINK = "capicxx-dbus-gen"

require capicxx-native.inc

SRC_URI = "${BASE_URL}/generator/${BASE_VER}/${PV}/commonapi_dbus_generator.zip"
SRC_URI[md5sum] = "70a6f1cf7e42a94260385281a569a486"
SRC_URI[sha256sum] = "de165298b1062951dff07d99f491c8d8a3ca3c1a087de56bc01c083cf71fa742"
