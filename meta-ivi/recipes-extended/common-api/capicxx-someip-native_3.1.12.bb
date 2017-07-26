SUMMARY = "Common API C++ SOME/IP generator"

LAUNCHER_BASE = "commonapi-someip-generator"
LAUNCHER_LINK = "capicxx-someip-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_someip_generator.zip"
SRC_URI[md5sum] = "6a2ec8b7a26adef97e524d4d6c1962de"
SRC_URI[sha256sum] = "2c9608630a0fa5341ca7aa5960b70904a0ed5ec856b25400a61aa7034f6dc0d1"
