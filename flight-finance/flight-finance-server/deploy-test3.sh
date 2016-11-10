#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/flight-finance \
         -p cana/flight-finance/flight-finance-server \
         -r test --host 192.168.1.13 -u root -a tz-cana -bin flight-finance-server \
         -c cana/flight-finance.settings.gradle \
         $lastarg -dz true -dzpn flight-finance-server \

