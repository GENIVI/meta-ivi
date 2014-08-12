# Base this image on generic IVI image
require recipes-yocto-ivi/images/ivi-image.inc

PREFERRED_VERSION_weston = "1.4.%"

PV = "0.1+snapshot-${DATE}"


IMAGE_INSTALL_append = " \
    packagegroup-abstract-component-p1 \
    packagegroup-placeholder-component-p1 \
    packagegroup-specific-component-p2 \
    packagegroup-abstract-component-p2 \
    packagegroup-specific-component-p1 \
    packagegroup-gdp-qt5 \
    packagegroup-gdp-gps \
    boost \
    automotive-message-broker \
    persistence-common-object \
    persistence-administrator \
    "
