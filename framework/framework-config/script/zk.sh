#!/bin/sh
 
# This script start and stop the index server
basepath=`pwd`
libpath=.:$basepath/conf:$basepath/lib/*
libclass=com.travelzen.framwork.config.app.AppMain
 
#(($#!=1))&&{ usage; exit 1; }

start() {
test -d log || mkdir log -p
java -Xms128M -Xmx1024M -classpath $libpath $libclass $1>> log/history.log 2>&1 &
}
 
usage(){
echo "syntax error!"
echo "Usage:"
echo "$(basename $0) clear|loadAll|update"
}

if [ $# != 1 ];
then
  usage; exit 1;
fi
case $1 in
clear)    start clear;;
loadAll)    start loadAll;;
update) 	start loadUpdate;;
*)        usage;;
esac
