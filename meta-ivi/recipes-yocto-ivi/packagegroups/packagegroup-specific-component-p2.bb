DESCRIPTION = "GENIVI P2 Specific components (SC)"
LICENSE = "MIT"
PR = "r1"

inherit packagegroup

PACKAGES = "\
    packagegroup-specific-component-p2 \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    common-api-c++-dbus \
    vsomeip \
    fuse \
    pulseaudio-server \
    quota \
    sqlite3 \
    "

