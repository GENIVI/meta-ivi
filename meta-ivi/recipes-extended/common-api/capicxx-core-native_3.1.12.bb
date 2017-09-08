SUMMARY = "Common API C++ core generator"

LAUNCHER_BASE = "commonapi-generator"
LAUNCHER_LINK = "capicxx-core-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi-generator.zip"
SRC_URI[md5sum] = "c54da4b1ccbb51e707f2527613e89f76"
SRC_URI[sha256sum] = "730526556222e888850b49c5371baa7eaffbcc824c318512d45b5115e5546282"
