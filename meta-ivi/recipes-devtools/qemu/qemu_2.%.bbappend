PACKAGECONFIG_append = " virtfs libusb"

EXTRA_OECONF += " --audio-drv-list=pa"
EXTRA_OECONF_remove_pn-nativesdk-qemu = "--audio-drv-list=pa"
