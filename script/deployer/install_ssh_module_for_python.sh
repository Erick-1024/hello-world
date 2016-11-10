#!/bin/bash

if [ ! -x ~/temp/tools ]
then
	echo "新建目录下载\n"
	mkdir -p ~/temp/tools
else
	echo "下载目录已经存在\n"
	
fi

cd ~/temp/tools


if [ ! -x ssh-1.7.14 ]
then
	rm -rf *
	echo "当前路径为 $(pwd) \n"
	echo "ssh包开始下载\n"
	wget "https://pypi.python.org/packages/source/s/ssh/ssh-1.7.14.tar.gz" -O "ssh-1.7.14.tar.gz"
	tar -zxvf ssh-1.7.14.tar.gz
else
	echo "ssh包已经下载\n"
fi

cd ssh-1.7.14

chmod a+x setup.py

sudo python setup.py build
sudo python setup.py install
