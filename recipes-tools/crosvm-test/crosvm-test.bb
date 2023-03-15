SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Install prebuilt guest vm binaries"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
RRECOMMENDS:${PN} += " \
	kernel-module-tun \
	kernel-module-nf-tables \
	kernel-module-nft-chain-nat-ipv4 \
	kernel-module-nft-chain-route-ipv4 \
	kernel-module-nft-masq-ipv4 \
	kernel-module-nft-na \
	kernel-module-ipt-masquerade \
	kernel-module-xt-masquerade \
	kernel-module-xt-state \
"

KERNEL_MODULE_AUTOLOAD += " tun nf_nat"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI += " file://crosvm \
             file://Image \
             file://rootfs.ext4 \
             file://setup_network \
           "
FILES:${PN} += " \
	${bindir}/crosvm \
	${datadir}/Image \
	${datadir}/rootfs.ext4 \
	${bindir}/setup_network \
"

#inherit arch
#do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}
    install -m 0770 ${WORKDIR}/Image ${D}/${datadir}/
    install -m 0770 ${WORKDIR}/rootfs.ext4 ${D}/${datadir}/
    install -d ${D}${bindir}
    install -m 0770 ${WORKDIR}/crosvm ${D}/${bindir}/
    install -m 0770 ${WORKDIR}/setup_network ${D}/${bindir}/
}
