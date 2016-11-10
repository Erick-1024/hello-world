#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/asset/asset-server \
         -p cana/asset/asset-server \
         -r test --host 192.168.1.7 -u root -a tz-cana -bin asset-server \
         -c cana/asset.settings.gradle \
         $lastarg -dz true -dzpn asset-server \

