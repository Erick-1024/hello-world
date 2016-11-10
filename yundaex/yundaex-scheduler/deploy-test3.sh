#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
host="192.168.1.13"

$bin -t $tops -s /data/apps/credit \
         -p cana/credit/credit-scheduler \
         -r test --host $host -u root -a tz-cana -bin credit-scheduler \
         -c cana/credit.settings.gradle \
         -dz true -dzpn credit-scheduler \
