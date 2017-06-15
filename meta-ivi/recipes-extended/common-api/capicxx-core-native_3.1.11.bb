SUMMARY = "Common API C++ core generator"

LAUNCHER_BASE = "commonapi-generator"
LAUNCHER_LINK = "capicxx-core-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi-generator.zip"
SRC_URI[md5sum] = "9b6006770c1f883157a316d939f75cb8"
SRC_URI[sha256sum] = "e919f525f2369fa56a1e493b093fd34ba25c1e2ea35cba174e841c9687d327ea"
