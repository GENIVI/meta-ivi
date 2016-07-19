SUMMARY = "Common API C++ SOME/IP generator"

LAUNCHER_BASE = "commonapi-someip-generator"
LAUNCHER_LINK = "capicxx-someip-gen"

require capicxx-native.inc

SRC_URI = "${BASE_URL}/generator/${BASE_VER}/${PV}/commonapi_someip_generator.zip"
SRC_URI[md5sum] = "23196ec64737ea85b96aa754d17cf01c"
SRC_URI[sha256sum] = "5135d9df40a27d29f60785db9812a65395e077b3e61f3beaa15d08c6094f8e0d"
