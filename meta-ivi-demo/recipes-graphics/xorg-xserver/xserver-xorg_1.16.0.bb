require xserver-xorg.inc

# Misc build failure for master HEAD
SRC_URI += "file://fix_open_max_preprocessor_error.patch \
            file://xorg-CVE-2013-6424.patch \
            file://xshmfence-option.patch \
           "

SRC_URI[md5sum] = "8a9ff6ee9907360f09b5bdabb8089502"
SRC_URI[sha256sum] = "38330a03c5f7a38745a3f9816cf069db5b8844eebb358fd39dbabf61f32cd06b"


DEPENDS_append =" libepoxy"

PACKAGECONFIG_remove = "glx"

EXTRA_OECONF += "--disable-docs --disable-devel-docs \
                --enable-xwayland --disable-xorg --disable-xvfb --disable-xnest \
                --disable-xquartz --disable-xwin \
                "

# These extensions are now integrated into the server, so declare the migration
# path for in-place upgrades.

RREPLACES_${PN} =  "${PN}-extension-dri \
                    ${PN}-extension-dri2 \
                    ${PN}-extension-record \
                    ${PN}-extension-extmod \
                    ${PN}-extension-dbe \
                   "
RPROVIDES_${PN} =  "${PN}-extension-dri \
                    ${PN}-extension-dri2 \
                    ${PN}-extension-record \
                    ${PN}-extension-extmod \
                    ${PN}-extension-dbe \
                   "
RCONFLICTS_${PN} = "${PN}-extension-dri \
                    ${PN}-extension-dri2 \
                    ${PN}-extension-record \
                    ${PN}-extension-extmod \
                    ${PN}-extension-dbe \
                   "
