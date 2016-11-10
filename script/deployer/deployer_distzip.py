#!/usr/bin/python
# coding=UTF-8

import os
import sys
import paramiko
import argparse
from datetime import *

parser = argparse.ArgumentParser(description='Process some integers.')
parser.add_argument("-t", "--tops",
                  dest="tops", required=True,
                  help="absolute path of tz(tops) derectory")
parser.add_argument("-s", "--server-directory",
                  dest="server_dir", required=True,
                  help="absolute path of target directory on server")
parser.add_argument("-p", "--project_path",
                  dest="project_path", required=True,
                  help="relative path of gradle project file, relative to tz(tops) directory")
parser.add_argument("-r", "--profile",
                  dest="profile",
                  help="profile for gradle building")
parser.add_argument("--host",
                  dest="host", required=True,
                  help="address of remote host")
parser.add_argument("-o", "--port",
                  dest="port", type=int, default=22,
                  help="port of remote ssh service")
parser.add_argument("-u", "--username",
                  dest="username", required=True,
                  help="username of remote host")
parser.add_argument("-a", "--password",
                  dest="password", required=True,
                  help="password of remote user")
parser.add_argument("-bin","--bin_script",
                  dest="bin", required=True,
                  help="script to start|stop")
parser.add_argument("--not-refresh-dependencies",
                  dest="not_refresh_dependencies", required=False, 
                  help="do not use '--refresh-dependencies', when execute gradle.")
parser.add_argument("-dz","--distZip",
                  dest="distZip", required=False, 
                  help="use 'gradle distZip' cmd build distribution archiver.")
parser.add_argument("-dzpn","--distZipProjectName",
                  dest="distZipProjectName", required=False, 
                  help="project name ,which using distZip build style.")
parser.add_argument("-dzLogPath","--distZipProjectLogPath",
                  dest="distZipProjectLogPath", required=False,
                  help="log path")
parser.add_argument("-c",
                  dest="settings", required=False,
                  help="see also gradle -c")
parser.add_argument("-innsa", "--isNotNeedStartArgument",
		  dest="isNotNeedStartArgument", required=False,
		  help="app start argument")

args = None
stuff = None
client = None
sftp = None

distZipFlag = None
distZipProjectName = None
distZipProjectLogPath = None
isNotNeedStartArgument = None

def coloration(s, color):
    return "\033[0;" + color + "m" + str(s) + "\033[0m"

def green(s):
    return coloration(s, "32")

def purple(s):
    return coloration(s, "35")

def localCmd(cmd):
    print green('==>') + ' executing "' + purple(cmd) + '"'
    os.system(cmd)

def sshExec(cmd):
    print green('==>') + ' executing "' + purple(cmd) + '"'
    return client.exec_command(cmd)

def sshCmd(cmd, ignoreError = False):
    stdin, stdout, stderr = sshExec(cmd)
    line = stdout.readline()
    while len(line) > 0:
        print line,
        line = stdout.readline()
    errInfo = stderr.read()
    if len(errInfo) > 0:
        print 'execute cmd failed => ' + errInfo
        if not ignoreError:
            sys.exit(1)

def sshRun(cmd):
    stdin, stdout, stderr = sshExec(cmd)
    if len(stderr.read()) > 0:
        sys.exit('execute cmd failed.')
    return stdout.read()

def stop():
    sshCmd('exit')
    localCmd("ps aux|grep deployer_distzip.py | grep -v 'grep'|awk '{print $2}' | xargs -l kill -9")
    print green('==>')+'exit'


def transfer(target, dest):
    print green('==>') + ' scp ' + purple(target) + ' --> ' + purple(dest)
    sftp.put(target, dest)

def package():
    cmd = 'gradle ' % stuff
    if not args.not_refresh_dependencies:
        cmd += '--refresh-dependencies '
    if distZipFlag:
    	cmd += 'clean distZip -p %(tops)s -b %(tops)s/%(project_path)s/build.gradle' % stuff
    else:	
    	cmd += 'clean zip -p %(tops)s -b %(tops)s/%(project_path)s/build.gradle' % stuff
    if args.profile:
        cmd += ' -Pprofile=' + args.profile
    if args.settings:
	stuff['settings'] = args.settings
	cmd += ' -c %(tops)s/%(settings)s ' % stuff
    localCmd(cmd)

def upload():
    if distZipFlag and distZipProjectName:
    	transfer('%(tops)s/%(project_path)s/target/distributions/%(distZipProjectName)s.zip' % stuff, '/tmp/%(distZipProjectName)s.zip' % stuff)
    else:
    	transfer('%(tops)s/%(project_path)s/target/distributions/%(project)s.zip' % stuff, '/tmp/%(project)s.zip' % stuff)

def deploy():

    sshCmd('mkdir -p %(server_dir)s' % stuff, True)

    if distZipFlag and distZipProjectName:
        sshCmd('cp -r /tmp/%(distZipProjectName)s.zip %(server_dir)s' % stuff, True)
        sshCmd("ps aux|grep %(distZipProjectName)s | grep -v 'grep'|awk '{print $2}' | xargs -l kill -9" % stuff, True)
    	sshCmd('rm -rf %(server_dir)s/%(distZipProjectName)s' % stuff, True)	
	sshCmd('cd %(server_dir)s && unzip -o  %(server_dir)s/%(distZipProjectName)s.zip' % stuff, True)
	if isNotNeedStartArgument:
            sshCmd('cd %(server_dir)s/%(distZipProjectName)s && nohup sh bin/%(bin)s >nohup.out 2>&1 &' % stuff, True)
        else:
            sshCmd('cd %(server_dir)s/%(distZipProjectName)s && nohup sh bin/%(bin)s >nohup.out 2>&1 &' % stuff, True)
    else:
    	sshCmd('test -f %(server_dir)s/%(project)s.zip && mv %(server_dir)s/%(project)s.zip %(server_dir)s/%(project)s_%(datetimestr)s.zip' % stuff, True)
    	sshCmd('cp -r /tmp/%(project)s.zip %(server_dir)s' % stuff, True) 
    	sshCmd('sh %(server_dir)s/bin/%(bin)s stop' % stuff, True)
        sshCmd('rm -rf %(server_dir)s/lib' % stuff, True)
        sshCmd('rm -rf %(server_dir)s/conf' % stuff, True)
        sshCmd('rm -rf %(server_dir)s/bin' % stuff, True)
    	sshCmd('cd %(server_dir)s && jar -xf %(server_dir)s/%(project)s.zip' % stuff, True)    
        sshCmd('cd %(server_dir)s && sh bin/%(bin)s start' % stuff, True)
	print green('==> ok, success to start server <===')

if __name__ == '__main__':
    args = parser.parse_args()

    stuff = {
            'tops':args.tops,
            'server_dir':args.server_dir,
            'project_path':args.project_path,
            'bin':args.bin,
    }

    distZipFlag = args.distZip
    distZipProjectName = args.distZipProjectName
    distZipProjectLogPath = args.distZipProjectLogPath
    isNotNeedStartArgument = args.isNotNeedStartArgument

    if not isNotNeedStartArgument or isNotNeedStartArgument.strip() != 'true':
        isNotNeedStartArgument = None

    if not distZipFlag or distZipFlag.strip() == '' or distZipFlag.strip() != 'true':
    	distZipFlag = None

    if distZipFlag and distZipFlag.strip() == 'true' and not distZipProjectName:
    	print green('==>') + ' please provide distZip project name.'
        sys.exit(0)
    else:
        stuff["distZipProjectName"] = distZipProjectName 


    stuff["datetimestr"] = datetime.now().strftime("%Y%m%d_%H%M%S")
    stuff['project'] = stuff['project_path'].split('/')[-1]

    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    client.connect(args.host, port=args.port, username=args.username, password=args.password)
    sftp = client.open_sftp()

    package()
    upload()
    deploy()
    stop()

