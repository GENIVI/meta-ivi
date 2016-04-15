SUMMARY = "GENIVI P2 Abstract components (AC)"
LICENSE = "MIT"
PR = "r1"

inherit packagegroup

PACKAGES = "\
    packagegroup-abstract-component-p2 \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    neard \
    node-health-monitor \
    persistence-client-library \
    persistence-administrator \
    "
