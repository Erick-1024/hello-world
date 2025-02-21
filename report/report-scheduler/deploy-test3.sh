#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/apps/report/report-scheduler \
         -p cana/report/report-scheduler \
         -r test3 --host 192.168.1.13 -u root -a tz-cana -bin report-scheduler \
         -c cana/report.settings.gradle \
         $lastarg -dz true -dzpn report-scheduler \