IMAGE_INSTALL = "packagegroup-core-boot-genivi ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

EXTRA_IMAGE_FEATURES = "debug-tweaks"
EXTRA_IMAGEDEPENDS += "qemu-native qemu-helper-native"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image buildhistory

IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

# Create SD image symlink correctly
IMAGE_POSTPROCESS_COMMAND_imx53qsb += "rename_symlink ; "

BUILDHISTORY_COMMIT = "1"
