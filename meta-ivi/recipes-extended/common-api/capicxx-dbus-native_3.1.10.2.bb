SUMMARY = "Common API C++ D-Bus generator"

LAUNCHER_BASE = "commonapi-dbus-generator"
LAUNCHER_LINK = "capicxx-dbus-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_dbus_generator.zip"
SRC_URI[md5sum] = "5b4863c98b9412344682f503ffda5eef"
SRC_URI[sha256sum] = "1c0ebf0fffc1d348e7aa644570b27b0828704cece3e99bbab5a30eda22d7cb78"
