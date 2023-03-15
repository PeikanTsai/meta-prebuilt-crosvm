This README file contains information on the contents of the meta-prebuilt-crosvm layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: <first dependency>
  branch: <branch name>

  URI: <second dependency>
  branch: <branch name>

  .
  .
  .

Patches
=======

Please submit any patches against the meta-prebuilt-crosvm layer to the xxxx mailing list (xxxx@zzzz.org)
and cc: the maintainer:

Maintainer: XXX YYYYYY <xxx.yyyyyy@zzzzz.com>

Table of Contents
=================

  I. Adding the meta-prebuilt-crosvm layer to your build
 II. Misc


I. Adding the meta-prebuilt-crosvm layer to your build
=================================================

Run 'bitbake-layers add-layer meta-prebuilt-crosvm'

II. Misc
========

- Download yocto and meta-prebuilt-crosvm
```
$ git clone git://git.yoctoproject.org/poky
$ git clone https://github.com/PeikanTsai/meta-prebuilt-crosvm.git
$ cd poky
$ source oe-init-build-env
```

- Add bblayer and patch local.conf
```
$ bitbake-layers add-layer ../../meta-prebuilt-crosvm
$ sed -i 's/#MACHINE ?= "qemuarm64"/MACHINE ?= "qemuarm64"/' conf/local.conf
$ echo 'IMAGE_INSTALL:append = " bash crosvm-test screen"' >> conf/local.conf
```
- Build
```
$ bitbake core-image-full-cmdline
```
- Run qemu
```
$ runqemu qemuarm64 nographic novga qemuparams="-m 2048 -machine virt,virtualization=on,gic-version=3"
```
- Launch first guest VM in qemu console
```
$ cd /usr/share
$ mkdir -p /var/empty
$ crosvm run -r rootfs.ext4 Image \
--cpus num-cores=1 --mem size=1024 \
--net tap-name=crosvm_tap \
--serial type=stdout,hardware=virtio-console,console,stdin
```
- Launch multiple VMs by using screen
```
$ echo "escape ^bb" > ~/.screenrc
$ screen
$ crosvm run -r rootfs.ext4 Image \
--cpus num-cores=1 --mem size=512 \
--serial type=stdout,hardware=virtio-console,console,stdin \
-s /var/tmp/

# Ctrl + B + c create new session

$ crosvm run -r rootfs.ext4 Image \
--cpus num-cores=1 --mem size=512 \
--serial type=stdout,hardware=virtio-console,console,stdin \
-s /var/tmp/
```

