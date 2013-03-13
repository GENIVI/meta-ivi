# Base this image on generic IVI image
include recipes-yocto-ivi/images/ivi-image.inc

PV = "4.0.0"
#PR = "r1"

IMAGE_INSTALL_append = " packagegroup-core-p1 packagegroup-core-p2"

# Add image specific packages
