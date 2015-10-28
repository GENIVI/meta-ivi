DEFAULT_PREFERENCE = "-1"

include recipes-multimedia/gstreamer/gstreamer1.0-plugins-bad.inc

#remove patch from poky that is not necessary in version 1.2.3
SRC_URI_remove = "file://configure-allow-to-disable-libssh2.patch"

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50"
SRC_URI[md5sum] = "cfd6f303c8df2740b27cc63b945decef"
SRC_URI[sha256sum] = "595d7911a9e6207dea37200587724bdbf841b81a5eb0730118be36976684278c"
S = "${WORKDIR}/gst-plugins-bad-${PV}"
