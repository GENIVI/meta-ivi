SUMMARY = "GENIVI Persistence Client Library"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823"

SRCREV = "0934ba263e69be8453cc3150f997dad28489f0c7"

SRC_URI = "git://git.projects.genivi.org/persistence/persistence-client-library.git \
          "

inherit pkgconfig

PR = "r2"

DEPENDS = "dbus dlt-daemon libitzam"

S = "${WORKDIR}/git"

inherit autotools-brokensep
PARALLEL_MAKE = ""

