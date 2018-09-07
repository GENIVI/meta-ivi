IMAGE_INSTALL = "packagegroup-core-boot-genivi ${CORE_IMAGE_EXTRA_INSTALL}"

EXTRA_IMAGE_FEATURES = "debug-tweaks"
EXTRA_IMAGEDEPENDS += "qemu-native qemu-helper-native"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image buildhistory

IMAGE_FEATURES += " package-management"

# Create SD image symlink correctly
IMAGE_POSTPROCESS_COMMAND_imx53qsb += "rename_symlink ; "

BUILDHISTORY_COMMIT = "1"

# Set default password for 'root' user
inherit extrausers
ROOTPASSWORD = "root"
ROOTUSERNAME = "root"
EXTRA_USERS_PARAMS ?= "usermod -P ${ROOTPASSWORD} ${ROOTUSERNAME};"
