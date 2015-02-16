# Base this image on generic IVI image
require recipes-yocto-ivi/images/ivi-image.inc

DESCRIPTION = "meta-ivi demo image"

PV = "0.1+snapshot-${DATE}"

IMAGE_FEATURES_append = " \
    ssh-server-openssh    \
    "

IMAGE_INSTALL_append = " \
    packagegroup-specific-component-p1 \
    packagegroup-abstract-component-p1 \
    packagegroup-placeholder-component-p1 \
    packagegroup-specific-component-p2 \
    packagegroup-abstract-component-p2 \
    "
