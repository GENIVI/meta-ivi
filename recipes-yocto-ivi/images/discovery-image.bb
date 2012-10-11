# Base this image on generic IVI image
include recipes-yocto-ivi/images/ivi-image.inc

PV = "2.0.2"
PR = "r0"

IMAGE_INSTALL_append = " packagegroup-core-p1 packagegroup-core-p2"

# Add image specific packages
IMAGE_INSTALL_append = " packagegroup-discovery-core-p1"
