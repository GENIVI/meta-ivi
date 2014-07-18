# Base this image on generic IVI image
require recipes-yocto-ivi/images/ivi-image.inc

PREFERRED_VERSION_weston = "1.4.%"

PV = "0.1+snapshot-${DATE}"


IMAGE_INSTALL_append = " \
    boost \
    "

