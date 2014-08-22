#
# Service API reference implementation
# Revision: 5.15 (Horizon)
# Homepage: https://collab.genivi.org/wiki/display/genivi/Bluetooth+Stack+Reference+Implementation
#
PV = "5.15"
PR = "horizon_r1"

SRC_URI_remove = "http://kernel.org/pub/linux/bluetooth/bluez-5.15.tar.xz"
SRC_URI_append = "git://git.gitorious.org/bluez/vudentzs-clone.git;protocol=http;branch=for-upstream"
SRCREV = "f76472e7983e52580afbf3a47f2f2e29e26514b9"

S = "${WORKDIR}/git"

