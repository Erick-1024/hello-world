#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /opt/mail-sender -p cat/mail-sender -r test --host 192.168.160.19 -u root -a Eft25801 -bin mail-sender -c cat.settings.gradle --isNotNeedStartArgument=true $lastarg -dz true -dzpn mail-sender -dzLogPath /data/log/tops/cruise-core-server\(localhost.localdomain\)-debug-current.log
