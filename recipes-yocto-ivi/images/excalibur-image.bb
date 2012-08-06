# Base this image on generic IVI image
include recipes-yocto-ivi/images/ivi-image.inc

IMAGE_INSTALL_append = " task-core-p1 task-core-p2"

# Add image specific packages
IMAGE_INSTALL_append = " task-excalibur-core-p1"
