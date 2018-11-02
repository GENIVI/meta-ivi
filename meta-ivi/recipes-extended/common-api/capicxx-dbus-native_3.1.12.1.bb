SUMMARY = "Common API C++ D-Bus generator"

LAUNCHER_BASE = "commonapi-dbus-generator"
LAUNCHER_LINK = "capicxx-dbus-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_dbus_generator.zip"
SRC_URI[sha256sum] = "6fb53b10ae49952383c8eb990995c6a3197055ccb51b199c8570b89b73522669"
