SUMMARY = "Common API C++ core generator"

LAUNCHER_BASE = "commonapi-generator"
LAUNCHER_LINK = "capicxx-core-gen"

require capicxx-native.inc

SRC_URI = "${BASE_URL}/generator/${BASE_VER}/${PV}/commonapi-generator.zip"
SRC_URI[md5sum] = "c12551e8f61da944b17a22c00d3e6559"
SRC_URI[sha256sum] = "538a923a1e596106b9f2ce0838fcf85fd81c4e09b0951c59ad2e2e72eb6d0534"
