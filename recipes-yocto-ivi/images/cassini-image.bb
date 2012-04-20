IMAGE_INSTALL = "task-core-boot-genivi ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL} task-core-p1 task-core-p2"

EXTRA_IMAGE_FEATURES = "debug-tweaks"
EXTRA_IMAGEDEPENDS += "qemu-native qemu-helper-native"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

# Check if image should be created
IMAGE_SD = '${@base_contains("IMAGE_FSTYPES", "sdimg", "sd", "core", d)}'
inherit ${IMAGE_SD}-image

IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

# Create SD image symlink currectly
IMAGE_POSTPROCESS_COMMAND_imx53qsb += "rename_symlink ; "
