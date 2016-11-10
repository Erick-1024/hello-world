#!/usr/bin/env bash
for i in `seq 1 $1`
do
python update.py tops-front-operator-biz tops-front-purchaser --action=restart --force=prod
sleep 2
done
