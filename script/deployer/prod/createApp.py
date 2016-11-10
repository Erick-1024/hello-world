import tzSsh
import os
import sys

def HELP():
    print 'python createApp.py ip port name format'

def remoteCmd(ssh, cmd):
    print cmd
    stdin, stdout, stderr = ssh.exec_command(cmd)
    std = stdout.read()
    err = stderr.read()
    if len(std) > 0:
        print "%s" % std.strip()
    if len(err) > 0:
        print "%s" % err.strip()
        return False
    return True

def appZip(name, ssh):
    remoteCmd(ssh, 'mkdir -p /data/%s' % name)
    remoteCmd(ssh, 'touch /data/%s/%s.zip' % (name, name))

def appWar(ip, port, jmx, name, ssh):
    remoteCmd(ssh, 'mkdir -p /data/%s' % name)
    remoteCmd(ssh, 'tar -xf  /data/apache-tomcat-latest.tar.gz -C /data/%s' % name)
    remoteCmd(ssh, 'touch /data/%s/webapps/%s.war' % (name, name))
    remoteCmd(ssh, 'sed -i "s/8080/%s/g" /data/%s/conf/server.xml' % (port, name))
    remoteCmd(ssh, 'sed -i "s/^#//" /data/%s/bin/setenv.sh' % name)
    remoteCmd(ssh, 'sed -i "s/-Djava.rmi.server.hostname=.*/-Djava.rmi.server.hostname=%s/" /data/%s/bin/setenv.sh' % (ip, name))
    remoteCmd(ssh, 'sed -i "s/-Dcom.sun.management.jmxremote.port=.*/-Dcom.sun.management.jmxremote.port=%s/" /data/%s/bin/setenv.sh' % (jmx, name))
    remoteCmd(ssh, 'cd /data/%s ; chgrp tomcat -R . ; chown tomcat -R logs/ run/ work/ ; chmod 750 * ; chmod g+r conf/* lib/* ; chmod o=--- -R .' % name)
     
if len(sys.argv) != 5:
    HELP()
    sys.exit()

ip = sys.argv[1]
port = int(sys.argv[2])
jmx = port - 5000
name = sys.argv[3]
format = sys.argv[4]
ssh, scp = tzSsh.sshClient(ip).connect()
if format == 'zip':
    appZip(name, ssh)
else:
    appWar(ip, port, jmx, name, ssh)