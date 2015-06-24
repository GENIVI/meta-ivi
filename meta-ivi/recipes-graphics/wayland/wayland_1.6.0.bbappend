PR = "r1"

# [PATCH] build fix up of "error: 'WL_POINTER_ERROR_ROLE' undeclared"
#  to support this macro, we have to go version 1.6.91
# Signed-off-by: Yong-iL Joh <yong-il.joh at windriver.com>
SRC_URI_remove = "http://wayland.freedesktop.org/releases/${BPN}-${PV}.tar.xz"
SRC_URI_append += "git://anongit.freedesktop.org/wayland/wayland.git;tag=1.6.91"
S = "${WORKDIR}/git"
