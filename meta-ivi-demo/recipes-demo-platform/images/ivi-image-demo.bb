# Base this image on generic IVI image
require recipes-yocto-ivi/images/ivi-image.inc

PV = "0.2+snapshot-${DATE}"


IMAGE_INSTALL_append = " \
    packagegroup-abstract-component-p1 \
    packagegroup-placeholder-component-p1 \
    packagegroup-specific-component-p2 \
    packagegroup-abstract-component-p2 \
    packagegroup-specific-component-p1 \
    packagegroup-gdp-am-poc \
    packagegroup-gdp-browser \
    packagegroup-gdp-qt5 \
    packagegroup-gdp-gps \
    boost \
    automotive-message-broker \
    dropbear \
    "
IMAGE_INSTALL_append_koelsch = " \
    wayland-wsegl \
    wayland-wsegl-dev \
    "
