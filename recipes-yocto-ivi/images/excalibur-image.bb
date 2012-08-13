LICENSE = "MIT"

# Base this image on generic IVI image
include recipes-yocto-ivi/images/ivi-image.inc

PV = "E-0.2"
PR = "r1"

IMAGE_INSTALL_append = " task-core-p1 task-core-p2"

# Add image specific packages
IMAGE_INSTALL_append = " task-excalibur-core-p1"
