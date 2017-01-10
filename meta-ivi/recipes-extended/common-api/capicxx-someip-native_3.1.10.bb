SUMMARY = "Common API C++ SOME/IP generator"

LAUNCHER_BASE = "commonapi-someip-generator"
LAUNCHER_LINK = "capicxx-someip-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_someip_generator.zip"
SRC_URI[md5sum] = "edd260db732a4c45476e200b12cea1bd"
SRC_URI[sha256sum] = "259a4524a6d24208461d6bba77becefffb8fc65e972e2ccb60fae565d6c75e35"
