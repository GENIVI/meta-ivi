SUMMARY = "Common API C++ core generator"

LAUNCHER_BASE = "commonapi-generator"
LAUNCHER_LINK = "capicxx-core-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi-generator.zip"
SRC_URI[md5sum] = "b8bacc0cc99760a06708795a63a5090e"
SRC_URI[sha256sum] = "27c88092f58f4570225784db87bad913beddea4a66d8d8a2487e708f74628a0f"
