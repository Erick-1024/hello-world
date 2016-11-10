#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/weixin-sender \
         -p cana/cat/weixin-sender \
         -r test --host 192.168.1.13 -u root -a tz-cana -bin weixin-sender \
         $lastarg -dz true -dzpn weixin-sender \

