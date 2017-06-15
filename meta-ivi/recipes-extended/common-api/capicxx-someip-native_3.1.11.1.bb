SUMMARY = "Common API C++ SOME/IP generator"

LAUNCHER_BASE = "commonapi-someip-generator"
LAUNCHER_LINK = "capicxx-someip-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_someip_generator.zip"
SRC_URI[md5sum] = "508a4b8578b3775230ab816dc211316e"
SRC_URI[sha256sum] = "f95a594f0f8d725e0c2b65b62f5ac38c31efa7a54fbaf6e692872204b6395d8a"
