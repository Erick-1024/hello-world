#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/auto_deploy.py
lastarg="-n"
if [ -z $1 ]; then
	lastarg=""
fi
$bin -t $tops -s /opt/tops-mediaserver/apache-tomcat-7.0.37 -w tops-mediaserver -p tops-common/tops-mediaserver -r rc --host 192.168.130.89 -u root -a NacVent5 $lastarg

