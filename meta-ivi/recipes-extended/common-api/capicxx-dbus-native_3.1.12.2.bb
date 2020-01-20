SUMMARY = "Common API C++ D-Bus generator"

LAUNCHER_BASE = "commonapi-dbus-generator"
LAUNCHER_LINK = "capicxx-dbus-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_dbus_generator.zip"
SRC_URI[sha256sum] = "ee7c4bcffc38b9526ae179a057d39d3a2170622efddc924fc91cc42835d082f5"
