#!/bin/sh

BASE_PATH=`dirname $0`

JAR_FILE=$BASE_PATH/../bankgate-server.jar

JAR_OPT="@server.java.options@"

start() {
java -jar $JAR_OPT $JAR_FILE >> nohup.log 2>&1 &
}

stop() {
kill -9  `ps aux | grep bankgate-server |grep -v grep | awk '{print $2}'`
}

restart(){
stop
start
}

usage(){
echo "syntax error!"
echo "Usage:"
echo "`basename $0` start|stop|restart"
}

if [ $# != 1 ];
then
  usage; exit 1;
fi
case $1 in
start)    start;;
stop)     stop;;
restart)  restart;;
*)        usage;;
esac
