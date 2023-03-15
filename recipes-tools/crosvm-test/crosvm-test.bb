SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Install prebuilt guest vm binaries"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " file://crosvm \
             file://Image \
             file://rootfs.ext4 \
           "
FILES:${PN} += " \
	${datadir}/crosvm \
	${datadir}/Image \
	${datadir}/rootfs.ext4 \
"

#inherit arch
#do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}
    install -m 0770 ${WORKDIR}/crosvm ${D}/${datadir}/
    install -m 0770 ${WORKDIR}/Image ${D}/${datadir}/
    install -m 0770 ${WORKDIR}/rootfs.ext4 ${D}/${datadir}/
}
