SRC_URI += " \
	file://virt.cfg;subdir=git \
	file://debug.cfg;subdir=git \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

