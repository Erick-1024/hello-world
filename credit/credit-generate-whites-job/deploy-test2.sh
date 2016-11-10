#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../..")"; pwd)
bin=$tops/cana/script/deployer/deployer_distzip.py

$bin -t $tops -s /data/apps/credit/credit-generate-whites-job \
         -p cana/credit/credit-generate-whites-job \
         -r test --host 10.3.21.145 -u root -a Abc12345 -bin credit-generate-whites-job \
         -c cana/credit.settings.gradle \
         -dz true -dzpn credit-generate-whites-job \
