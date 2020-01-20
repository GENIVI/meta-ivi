SUMMARY = "Common API C++ core generator"

LAUNCHER_BASE = "commonapi-generator"
LAUNCHER_LINK = "capicxx-core-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi-generator.zip"
SRC_URI[sha256sum] = "6de1720a80c6b61c1f20512e03f86c29b21f4df572c89e1f9b2e38923177b3ea"

