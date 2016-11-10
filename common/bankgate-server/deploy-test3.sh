#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
host="192.168.1.13"

$bin -t $tops -s /data/apps/bankgate/bankgate-server \
         -p cana/common/bankgate-server \
         -r test --host $host -u root -a tz-cana -bin bankgate-server \
         -c cana/common.settings.gradle \
         -dz true -dzpn bankgate-server

