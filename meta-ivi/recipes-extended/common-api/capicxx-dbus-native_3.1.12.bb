SUMMARY = "Common API C++ D-Bus generator"

LAUNCHER_BASE = "commonapi-dbus-generator"
LAUNCHER_LINK = "capicxx-dbus-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_dbus_generator.zip"
SRC_URI[md5sum] = "8b4449a8fd722c09df68a3216daac490"
SRC_URI[sha256sum] = "a8cebb9ae37e9e5324f5d55859eb07d208e3c60d71e99d6cdbfcfabaa8314090"
