# Base this image on generic IVI image
require recipes-yocto-ivi/images/ivi-image.inc

PV = "7.0+snapshot-${DATE}"

IMAGE_INSTALL_append = " \
    packagegroup-abstract-component-p1 \
    packagegroup-placeholder-component-p1 \
    packagegroup-specific-component-p2 \
    packagegroup-abstract-component-p2 \
    packagegroup-specific-component-p1 \
"
