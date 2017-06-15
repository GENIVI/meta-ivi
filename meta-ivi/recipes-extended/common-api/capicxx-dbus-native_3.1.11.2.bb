SUMMARY = "Common API C++ D-Bus generator"

LAUNCHER_BASE = "commonapi-dbus-generator"
LAUNCHER_LINK = "capicxx-dbus-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_dbus_generator.zip"
SRC_URI[md5sum] = "78b9318cc59c2e396ad5de1891afccec"
SRC_URI[sha256sum] = "7ff452745944ea1793ae095c9f7115b2d8aba3bd20f3a36435d479f602bcd14a"
