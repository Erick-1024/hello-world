#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/member/member-server \
         -p cana/member/member-server \
         -r test --host 192.168.1.13 -u root -a tz-cana -bin member-server \
         -c cana/member.settings.gradle \
         $lastarg -dz true -dzpn member-server \

