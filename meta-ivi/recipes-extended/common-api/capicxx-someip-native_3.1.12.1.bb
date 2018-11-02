SUMMARY = "Common API C++ SOME/IP generator"

LAUNCHER_BASE = "commonapi-someip-generator"
LAUNCHER_LINK = "capicxx-someip-gen"

require capicxx-native.inc

SRC_URI = "https://github.com/GENIVI/${_BPN}-tools/releases/download/${PV}/commonapi_someip_generator.zip"
SRC_URI[sha256sum] = "2983ed31d4f99e1b018ef8cc12fe68d08bf8379b845fee2c324f655731c42f9a"
