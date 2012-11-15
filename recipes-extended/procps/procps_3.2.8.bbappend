PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS := "${THISDIR}/${PN}-${PV}"
SRC_URI += "file://procps-3.2.8-ps-cgroup.patch"
