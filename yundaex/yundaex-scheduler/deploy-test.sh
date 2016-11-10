#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
host="192.168.1.7"

$bin -t $tops -s /data/apps/yundaex \
         -p cana/yundaex/yundaex-scheduler \
         -r test --host $host -u root -a tz-cana -bin yundaex-scheduler \
         -c cana/yundaex.settings.gradle \
         -dz true -dzpn yundaex-scheduler \
