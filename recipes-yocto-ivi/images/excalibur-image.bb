# Base this image on discovery image
include recipes-yocto-ivi/images/discovery-image.bb

# Add image specific packages
IMAGE_INSTALL += "task-excalibur-core-p1"