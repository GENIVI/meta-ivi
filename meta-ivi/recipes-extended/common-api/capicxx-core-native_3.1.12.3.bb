SUMMARY = "Common API C++ core generator"

LAUNCHER_BASE = "commonapi-generator"
LAUNCHER_LINK = "capicxx-core-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi-generator.zip"
SRC_URI[sha256sum] = "979abe3c2ff29e6c5e8a61cdfbd262bf837e6a26201593c5e6e38160978f2a21"
