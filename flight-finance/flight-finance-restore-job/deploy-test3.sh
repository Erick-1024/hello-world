#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
host="192.168.1.13"

$bin -t $tops -s /data/apps/flight-finance \
         -p cana/flight-finance/flight-finance-restore-job \
         -r test --host $host -u root -a tz-cana -bin flight-finance-restore-job \
         -c cana/flight-finance.settings.gradle \
         -dz true -dzpn flight-finance-restore-job \
