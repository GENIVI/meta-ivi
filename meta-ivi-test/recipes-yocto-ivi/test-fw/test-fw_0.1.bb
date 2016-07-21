SUMMARY = "Simple Test script for meta-ivi"
DESCRIPTION = "This component provides a simple test framework for testing meta-ivi s/w"
HOMEPAGE = "https://github.com/GENIVI/meta-ivi"
SECTION = "test"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "file://test-fw.sh \
    "

do_compile() {
}

do_install() {
   install -d ${D}/opt/tests/
   install -m 0755 ${WORKDIR}/test-fw.sh ${D}/opt/tests/
}

FILES_${PN} += " \
   /opt/tests/ \
   "
