#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/report/report-server \
         -p cana/report/report-server \
         -r test --host 192.168.1.7 -u root -a tz-cana -bin report-server \
         -c cana/report.settings.gradle \
         $lastarg -dz true -dzpn report-server \

