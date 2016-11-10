#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/message/message-server \
         -p cana/message/message-server \
         -r test --host 192.168.1.13 -u root -a tz-cana -bin message-server \
         -c cana/message.settings.gradle \
         $lastarg -dz true -dzpn message-server \

