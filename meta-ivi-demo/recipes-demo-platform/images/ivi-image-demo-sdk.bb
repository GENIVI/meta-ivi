require ivi-image-demo.bb

DESCRIPTION = "meta-ivi demo image that includes everything within \
ivi-image-demo plus meta-toolchain, development headers and libraries to \
form a standalone SDK."

inherit populate_sdk

QT5PKG = ""

IMAGE_FEATURES_append = "\
	debug-tweaks         \
	dev-pkgs             \
	eclipse-debug        \
	${QT5PKG}            \
	tools-debug          \
	tools-profile        \
	tools-sdk            \
	tools-testapps       \
	"

IMAGE_INSTALL_append  = "\
	kernel-dev           \
	"
