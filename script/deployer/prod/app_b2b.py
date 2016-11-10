#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import os
import multiprocessing
import subprocess
import threading
#import tzOps
import packaging
#import tzZabbix
import tzSsh
import time
import log
import re

class remoteExec(multiprocessing.Process):
    def __init__(self, project, env, jiraid, ops_tag, update_tag, rollback_tag, tasks, act, options, lock):
        self.project = project
        self.env = env
        self.jiraid = jiraid
        self.ops_tag = ops_tag
        self.update_tag = update_tag
        self.rollback_tag = rollback_tag
        self.tasks = tasks
        self.act = act
        self.options = options
        self.lock = lock
        super(remoteExec, self).__init__(name = self.project)
    
    def localCmd(self, cmd):
        self.remote_log.DebugLog(cmd)
        if not '--check' in self.options:
            p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            retval = p.wait()
            if retval != 0:
                self.remote_log.WarnLog(p.stderr.read())
            out = p.stdout.read()
            self.remote_log.DebugLog(out)
        return out
    
    def remoteCmd(self, cmd):
        self.remote_log.DebugLog(cmd)
        if not '--check' in self.options:
            stdin, stdout, stderr = self.ssh.exec_command(cmd)
            std = stdout.read()
            err = stderr.read()
            if len(std) > 0:
                self.remote_log.DebugLog("%s" % std.strip())
            if len(err) > 0:
                self.remote_log.ErrorLog("%s" % err.strip())
                return False
        return True
    
    def ReValue(self, cmd):
        self.remote_log.DebugLog(cmd)
        stdin, stdout, stderr = self.ssh.exec_command(cmd)
        out = stdout.read()
        err = stderr.read()
        if len(out) > 0:
            self.remote_log.DebugLog("%s" % out.strip())
        if len(err) > 0:
            self.remote_log.ErrorLog("%s" % err.strip())
        return out

    def act_update(self):
        md5sum_new, md5sum_old, md5sum_last, md5sum_tag, md5sum_tmp = '0', '1', '2', '3', '4'
#        if '--old' in self.options:
#            if self.thrift_server == 'yes':
#                self.remote_log.DebugLog("scp %s_%s_%s_%s %s_%s" % (self.update_package, self.update_tag, self.env, self.ip.split('.')[-1], self.package_source, self.update_tag))
#                if not '--check' in self.options:
#                    md5sum_new = os.popen('md5sum %s_%s_%s_%s' % (self.update_package, self.update_tag, self.env, self.ip.split('.')[-1])).read().split()[0]
#                    self.scp.put('%s_%s_%s_%s' % (self.update_package, self.update_tag, self.env, self.ip.split('.')[-1]), '%s_%s' % (self.package_source, self.update_tag))
#            else:
#                self.remote_log.DebugLog("scp %s_%s_%s %s_%s" % (self.update_package, self.update_tag, self.env, self.package_source, self.update_tag))
#                if not '--check' in self.options:
#                    md5sum_new = os.popen('md5sum %s_%s_%s' % (self.update_package, self.update_tag, self.env)).read().split()[0]
#                    self.scp.put('%s_%s_%s' % (self.update_package, self.update_tag, self.env), '%s_%s' % (self.package_source, self.update_tag))
#        else:
        self.remote_log.DebugLog("scp %s_%s %s_%s" % (self.update_package, self.update_tag, self.package_source, self.update_tag))
        if not '--check' in self.options:
            md5sum_new = os.popen('md5sum %s_%s' % (self.update_package, self.update_tag)).read().split()[0]
            self.scp.put('%s_%s' % (self.update_package, self.update_tag), '%s_%s' % (self.package_source, self.update_tag))
        if self.remoteCmd('test -f %s_%s || echo "%s_%s not found" 1>&2' % (self.package_source, self.update_tag, self.package_source, self.update_tag)):
            listen_port = self.ReValue(self.java_port).strip()
            if self.offline != '' and self.env == 'prod':
                for node in self.offline.split(','):
                    self.remote_log.DebugLog("offline: %s %s %s" % (node, self.ip, listen_port))
#                    if not '--check' in self.options and not '--noupdateops' in self.options:
#                        self.lock.acquire()
#                        self.remote_log.DebugLog(tzOps.ops().offline(node, self.ip, listen_port))
#                        self.lock.release()
            self.remoteCmd(self.java_stop)
            time.sleep(5)
            for clean_cache in self.clean_cache:
                self.remoteCmd(clean_cache % self.java_path)
            #print "rm --------------------------- work --------------------"
            '''
            if self.project == 'tz-eterm-interface-web' and self.ip == '192.168.250.18':
                self.remoteCmd('mv %s/webapps/tz-eterm-interface-web/tz-eterm-interface-web.war %s' % (self.java_path, self.package_target))
            '''
            md5sum_old = self.ReValue('md5sum %s' % self.package_target).split()[0].strip()
            self.remoteCmd('test -f ~/%s_last && rm -rf ~/%s_last; mv %s ~/%s_last' % (self.package_name, self.package_name, self.package_target, self.package_name))
            self.remoteCmd('cp %s_%s %s' % (self.package_source, self.update_tag, self.package_target))
            md5sum_tmp = self.ReValue('md5sum %s' % self.package_target).split()[0].strip()
            if md5sum_new == md5sum_tmp or '--check' in self.options:
                '''
                if self.project == 'tz-eterm-interface-web' and self.ip == '192.168.250.18':
                    self.remoteCmd('mv %s %s/webapps/tz-eterm-interface-web/tz-eterm-interface-web.war' % (self.package_target, self.java_path))
                    self.remoteCmd('cd %s/webapps/tz-eterm-interface-web/ && jar -xf tz-eterm-interface-web.war' % self.java_path)
                '''
                self.remoteCmd(self.java_start)
                if self.offline != '' and self.offline != 'ip_port' and self.env == 'prod':
                    count = 1
                    self.remote_log.DebugLog('wait online')
                    self.remote_log.DebugLog('wait server start .. %s' % count)
                    while True:
                        count = count + 1
                        htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s/%s' % (self.ip, listen_port, self.project))
                        try:
                            htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                        except IndexError as e:
                            self.remote_log.WarnLog(e)
                            time.sleep(5)
                            continue
                        if re.match('(2|3)\d\d', htmlstate):
                            break
                        self.remote_log.ErrorLog(htmlhead)
                        self.remote_log.ErrorLog('wait server start .. %s' % count)
                        time.sleep(10)
                    self.remote_log.DebugLog('server start OK!')
                    for node in self.offline.split(','):
                        self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                        if not '--check' in self.options and not '--noupdateops' in self.options:
#                            self.lock.acquire()
#                            self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                            self.lock.release()
                elif self.offline == 'ip_port' and self.env == 'prod':
                    count = 1
                    self.remote_log.DebugLog('wait online')
                    self.remote_log.DebugLog('wait server start .. %s' % count)
                    while True:
                        count = count + 1
                        htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s' % (self.ip, listen_port))
                        try:
                            htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                        except IndexError as e:
                            self.remote_log.WarnLog(e)
                            time.sleep(5)
                            continue
                        if re.match('200', htmlstate):
                            break
                        self.remote_log.ErrorLog(htmlhead)
                        self.remote_log.ErrorLog('wait server start .. %s' % count)
                        time.sleep(10)
                    self.remote_log.DebugLog('server start OK!')
                    for node in self.offline.split(','):
                        self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                        if not '--check' in self.options and not '--noupdateops' in self.options:
#                            self.lock.acquire()
#                            self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                            self.lock.release()
                self.remoteCmd('cp %s_%s ~/%s_%s' % (self.package_source, self.update_tag, self.package_name, self.update_tag))
                md5sum_last = self.ReValue('md5sum ~/%s_last' % self.package_name).split()[0].strip()
                md5sum_tag = self.ReValue('md5sum ~/%s_%s' % (self.package_name, self.update_tag)).split()[0].strip()       
                if (md5sum_last != md5sum_old or md5sum_tag != md5sum_new) and not '--check' in self.options:
                    self.remote_log.ErrorLog('backup: the MD5 value changes')
            else:
                self.remote_log.ErrorLog('update: the MD5 value changes')
        else:
            pass
        
    def act_rollback(self):
        md5sum_rollback, md5sum_revoke = '', ''
        if self.remoteCmd('test -f ~/%s_%s || echo "%s_%s not found" 1>&2' % (self.package_name, self.rollback_tag, self.package_name, self.rollback_tag)):
            md5sum_rollback = self.ReValue('md5sum ~/%s_%s' % (self.package_name, self.rollback_tag)).split()[0].strip()
            listen_port = self.ReValue(self.java_port).strip()
            if self.offline != '' and self.env == 'prod':
                for node in self.offline.split(','):
                    self.remote_log.DebugLog("offline: %s %s %s" % (node, self.ip, listen_port))
#                    if not '--check' in self.options and not '--noupdateops' in self.options:
#                        self.lock.acquire()
#                        self.remote_log.DebugLog(tzOps.ops().offline(node, self.ip, listen_port))
#                        self.lock.release()
            self.remoteCmd(self.java_stop)
            time.sleep(5)
            for clean_cache in self.clean_cache:
                self.remoteCmd(clean_cache % self.java_path)
            '''
            if self.project == 'tz-eterm-interface-web' and self.ip == '192.168.241.84':
                self.remoteCmd('mv %s/webapps/tz-eterm-interface-web/tz-eterm-interface-web.war %s' % (self.java_path, self.package_target))
            '''
            md5sum_old = self.ReValue('md5sum %s' % self.package_target).split()[0].strip()
            self.remoteCmd('test -f ~/%s_revoke || rm -rf ~/%s_revoke ; mv %s ~/%s_revoke' % (self.package_name, self.package_name, self.package_target, self.package_name))
            self.remoteCmd('cp ~/%s_%s %s' % (self.package_name, self.rollback_tag, self.package_target))
            md5sum_tmp = self.ReValue('md5sum %s' % self.package_target).split()[0].strip()
            if md5sum_rollback == md5sum_tmp or '--check' in self.options:
                '''
                if self.project == 'tz-eterm-interface-web' and self.ip == '192.168.241.84':
                    self.remoteCmd('mv %s %s/webapps/tz-eterm-interface-web/tz-eterm-interface-web.war' % (self.package_target, self.java_path))
                    self.remoteCmd('cd %s/webapps/tz-eterm-interface-web/ && jar -xf tz-eterm-interface-web.war' % self.java_path)
                '''
                self.remoteCmd(self.java_start)
                if self.offline != '' and self.offline != 'ip_port' and self.env == 'prod':
                    count = 1
                    self.remote_log.DebugLog('wait online')
                    self.remote_log.DebugLog('wait server start .. %s' % count)
                    while True:
                        count = count + 1
                        htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s/%s' % (self.ip, listen_port, self.project))
                        try:
                            htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                        except IndexError as e:
                            self.remote_log.WarnLog(e)
                            time.sleep(5)
                            continue
                        if re.match('(2|3)\d\d', htmlstate):
                            break
                        self.remote_log.ErrorLog(htmlhead)
                        self.remote_log.ErrorLog('wait server start .. %s' % count)
                        time.sleep(10)
                    self.remote_log.DebugLog('server start OK!')
                    for node in self.offline.split(','):
                        self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                        if not '--check' in self.options and not '--noupdateops' in self.options:
#                            self.lock.acquire()
#                            self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                            self.lock.release()
                elif self.offline == 'ip_port' and self.env == 'prod':
                    count = 1
                    self.remote_log.DebugLog('wait online')
                    self.remote_log.DebugLog('wait server start .. %s' % count)
                    while True:
                        count = count + 1
                        htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s' % (self.ip, listen_port))
                        try:
                            htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                        except IndexError as e:
                            self.remote_log.WarnLog(e)
                            time.sleep(5)
                            continue
                        if re.match('200', htmlstate):
                            break
                        self.remote_log.ErrorLog(htmlhead)
                        self.remote_log.ErrorLog('wait server start .. %s' % count)
                        time.sleep(10)
                    self.remote_log.DebugLog('server start OK!')
                    for node in self.offline.split(','):
                        self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                        if not '--check' in self.options and not '--noupdateops' in self.options:
#                            self.lock.acquire()
#                            self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                            self.lock.release()
                md5sum_revoke = self.ReValue('md5sum ~/%s_revoke' % self.package_name).split()[0].strip()
                if md5sum_revoke != md5sum_old and not '--check' in self.options:
                    self.remote_log.ErrorLog('revoke: the MD5 value changes')
            else:
                self.remote_log.ErrorLog('rollback: the MD5 value changes')
        else:
            pass
    
    def act_restart(self):
        listen_port = self.ReValue(self.java_port).strip()
        if self.offline != '' and self.env == 'prod':
            for node in self.offline.split(','):
                self.remote_log.DebugLog("offline: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().offline(node, self.ip, listen_port))
#                    self.lock.release()
        self.remoteCmd(self.java_stop)
        time.sleep(5)
        self.remoteCmd(self.java_start)
        if self.offline != '' and self.offline != 'ip_port' and self.env == 'prod':
            count = 1
            self.remote_log.DebugLog('wait online')
            self.remote_log.DebugLog('wait server start .. %s' % count)
            while True:
                count = count + 1
                htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s/%s' % (self.ip, listen_port, self.project))
                try:
                    htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                except IndexError as e:
                    self.remote_log.WarnLog(e)
                    time.sleep(5)
                    continue
                if re.match('(2|3)\d\d', htmlstate):
                    break
                self.remote_log.ErrorLog(htmlhead)
                self.remote_log.ErrorLog('wait server start .. %s' % count)
                time.sleep(10)
            self.remote_log.DebugLog('server start OK!')
            for node in self.offline.split(','):
                self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                    self.lock.release()
        elif self.offline == 'ip_port' and self.env == 'prod':
            count = 1
            self.remote_log.DebugLog('wait online')
            self.remote_log.DebugLog('wait server start .. %s' % count)
            while True:
                count = count + 1
                htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s' % (self.ip, listen_port))
                try:
                    htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                except IndexError as e:
                    self.remote_log.WarnLog(e)
                    time.sleep(5)
                    continue
                if re.match('200', htmlstate):
                    break
                self.remote_log.ErrorLog(htmlhead)
                self.remote_log.ErrorLog('wait server start .. %s' % count)
                time.sleep(10)
            self.remote_log.DebugLog('server start OK!')
            for node in self.offline.split(','):
                self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                    self.lock.release()
            
    def act_stop(self):
        listen_port = self.ReValue(self.java_port).strip()
        if self.offline != '' and self.env == 'prod':
            for node in self.offline.split(','):
                self.remote_log.DebugLog("offline: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().offline(node, self.ip, listen_port))
#                    self.lock.release()
        self.remoteCmd(self.java_stop)
            
    def act_start(self):
        listen_port = self.ReValue(self.java_port).strip()
        self.remoteCmd(self.java_start)
        if self.offline != '' and self.offline != 'ip_port' and self.env == 'prod':
            count = 1
            self.remote_log.DebugLog('wait online')
            self.remote_log.DebugLog('wait server start .. %s' % count)
            while True:
                count = count + 1
                htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s/%s' % (self.ip, listen_port, self.project))
                try:
                    htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                except IndexError as e:
                    self.remote_log.WarnLog(e)
                    time.sleep(5)
                    continue
                if re.match('(2|3)\d\d', htmlstate):
                    break
                self.remote_log.ErrorLog(htmlhead)
                self.remote_log.ErrorLog('wait server start .. %s' % count)
                time.sleep(10)
            self.remote_log.DebugLog('server start OK!')
            for node in self.offline.split(','):
                self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                    self.lock.release()
        elif self.offline == 'ip_port' and self.env == 'prod':
            count = 1
            self.remote_log.DebugLog('wait online')
            self.remote_log.DebugLog('wait server start .. %s' % count)
            while True:
                count = count + 1
                htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s' % (self.ip, listen_port))
                try:
                    htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                except IndexError as e:
                    self.remote_log.WarnLog(e)
                    time.sleep(5)
                    continue
                if re.match('200', htmlstate):
                    break
                self.remote_log.ErrorLog(htmlhead)
                self.remote_log.ErrorLog('wait server start .. %s' % count)
                time.sleep(10)
            self.remote_log.DebugLog('server start OK!')
            for node in self.offline.split(','):
                self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                    self.lock.release()
    
    def act_offline(self):
        listen_port = self.ReValue(self.java_port).strip()
        if self.offline != '' and self.env == 'prod':
            for node in self.offline.split(','):
                self.remote_log.DebugLog("offline: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().offline(node, self.ip, listen_port))
#                    self.lock.release()
    
    def act_online(self):
        listen_port = self.ReValue(self.java_port).strip()
        if self.offline != '' and self.offline != 'ip_port' and self.env == 'prod':
            count = 1
            self.remote_log.DebugLog('wait online')
            self.remote_log.DebugLog('wait server start .. %s' % count)
            while True:
                count = count + 1
                htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s/%s' % (self.ip, listen_port, self.project))
                try:
                    htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                except IndexError as e:
                    self.remote_log.WarnLog(e)
                    time.sleep(5)
                    continue
                if re.match('(2|3)\d\d', htmlstate):
                    break
                self.remote_log.ErrorLog(htmlhead)
                self.remote_log.ErrorLog('wait server start .. %s' % count)
                time.sleep(10)
            self.remote_log.DebugLog('server start OK!')
            for node in self.offline.split(','):
                self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                    self.lock.release()
        elif self.offline == 'ip_port' and self.env == 'prod':
            count = 1
            self.remote_log.DebugLog('wait online')
            self.remote_log.DebugLog('wait server start .. %s' % count)
            while True:
                count = count + 1
                htmlhead = self.localCmd('curl -I --max-time 200 -q http://%s:%s' % (self.ip, listen_port))
                try:
                    htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
                except IndexError as e:
                    self.remote_log.WarnLog(e)
                    time.sleep(5)
                    continue
                if re.match('200', htmlstate):
                    break
                self.remote_log.ErrorLog(htmlhead)
                self.remote_log.ErrorLog('wait server start .. %s' % count)
                time.sleep(10)
            self.remote_log.DebugLog('server start OK!')
            for node in self.offline.split(','):
                self.remote_log.DebugLog("online: %s %s %s" % (node, self.ip, listen_port))
#                if not '--check' in self.options and not '--noupdateops' in self.options:
#                    self.lock.acquire()
#                    self.remote_log.DebugLog(tzOps.ops().online(node, self.ip, listen_port))
#                    self.lock.release()
    
    def act_test(self):
        listen_port = self.ReValue(self.java_port).strip()
        htmlhead = self.localCmd('curl -I -q http://%s:%s/%s' % (self.ip, listen_port, self.project))
        htmlstate = ''.join(re.findall('\d{3}', re.findall('^HTTP.*\n', htmlhead)[0]))
        if re.match('(2|3)\d\d', htmlstate):
            print 'ok'
        else:
            print 'not ok'
            
    def run(self):
        for self.ip in self.tasks[self.project]['servers'].keys():
            self.debug_file = self.tasks[self.project]['other'][self.ip]['debug_log']
            self.error_file = self.tasks[self.project]['other'][self.ip]['error_log']
            self.ssh_port = self.tasks[self.project]['other'][self.ip]['ssh_port']
            if self.ssh_port == '':
                self.ssh_port = 22
            self.ssh_method = self.tasks[self.project]['other'][self.ip]['ssh_method']
            if self.ssh_method == '':
                self.ssh_method = 'pkey'
            self.ssh_user = self.tasks[self.project]['other'][self.ip]['ssh_user']
            if self.ssh_user == '':
                self.ssh_user = 'root'
            self.ssh_passwd = self.tasks[self.project]['other'][self.ip]['ssh_passwd']
            if self.ssh_passwd == '':
                self.ssh_passwd = None
            self.ssh_key = self.tasks[self.project]['other'][self.ip]['ssh_key']
            if self.ssh_key == '':
                self.ssh_key = None
            self.remote_log = log.WriteLog(self.options, self.debug_file, self.error_file, '%s %s' % (self.project, self.ip))
            self.remote_log.TimeMarker()
            while True:
                try:
                    self.remote_log.DebugLog("connect to %s .." % self.ip)
                    self.ssh, self.scp = tzSsh.sshClient(self.ip).connect()
                except Exception as e:
                    time.sleep(10)
                    self.remote_log.ErrorLog("%s\nconnect to %s Failed\n\tAfter trying for 10 seconds" % (e, self.ip))
                    continue
                else:
                    self.remote_log.DebugLog("%s: connect to %s OK!" % (self.project, self.ip))
                    break
            for self.java_path in self.tasks[self.project]['servers'][self.ip]['java_path'].split(','):
                self.java_port = self.tasks[self.project]['servers'][self.ip]['java_port'] % self.java_path
                self.java_stop = self.tasks[self.project]['servers'][self.ip]['java_stop'] % self.java_path
                self.java_start = self.tasks[self.project]['servers'][self.ip]['java_start'] % self.java_path
                self.package_target = self.tasks[self.project]['servers'][self.ip]['package_target'] % self.java_path
                self.clean_cache = self.tasks[self.project]['servers'][self.ip]['clean_cache'].split(',')
                self.update_package = self.tasks[self.project]['servers'][self.ip]['update_package']
                self.rollback_package = self.tasks[self.project]['servers'][self.ip]['rollback_package']
                self.package_source = self.tasks[self.project]['servers'][self.ip]['package_source']
                self.offline = self.tasks[self.project]['offline']
                self.package_name = os.path.basename(self.package_target)
                self.thrift_server = self.tasks[self.project]['thrift_server']
                if 'rollback' == self.act:
                    self.act_rollback()
                elif 'update' == self.act:
                    self.act_update()
                elif 'restart' == self.act: 
                    self.act_restart()
                elif 'stop' == self.act:
                    self.act_stop()
                elif 'start' == self.act:
                    self.act_start()
                elif 'test' == self.act:
                    self.act_test()
                else:
                    pass
                
            if 'offline' == self.act:
                self.act_offline()
            elif 'online' == self.act:
                self.act_online()
            
#            if not '--check' in self.options:
#                retval = tzOps.ops().log(self.ops_tag, self.ip, self.project, self.jiraid)
#                if retval == '1':
#                    self.remote_log.DebugLog('tzOps: log to ops %s %s %s successfully' % (self.ops_tag, self.ip, self.project))
#                else:
#                    self.remote_log.WarnLog('tzOps: log to ops %s %s %s failed' % (self.ops_tag, self.ip, self.project))
#                    self.remote_log.WarnLog("tzOps: retval -> %s" % retval)
            self.ssh.close()
            self.remote_log.CloseLog()
            
        #if 'update' == self.act and not '--old' in self.options:
        #    self.localCmd('mv %s_%s %s_%s' % (self.update_package, self.update_tag, self.rollback_package, self.update_tag))
            
def check_package(tasks, update_tag, options, log_app, env):
    boolean = True
    no_package = []
    for project in tasks.keys():
        for ip in tasks[project]['servers'].keys():
            if not '--old' in options:
                if not os.path.isfile('%s_%s' % (tasks[project]['servers'][ip]['update_package'], update_tag)):
                    log_app.ErrorLog('%s_%s: %s_%s not found' % (project, ip, tasks[project]['servers'][ip]['update_package'], update_tag))
                    no_package.append(project)
                    boolean = False
                else:
                    log_app.DebugLog('%s_%s: %s_%s found' % (project, ip, tasks[project]['servers'][ip]['update_package'], update_tag))
            else:
                if tasks[project]['thrift_server'] == 'yes' and env == 'prod':
                    if not os.path.isfile('%s_%s_%s_%s' % (tasks[project]['servers'][ip]['update_package'], update_tag, env, ip.split(',')[-1])):
                        log_app.ErrorLog('%s_%s: %s_%s_%s_%s not found' % (project, ip, tasks[project]['servers'][ip]['update_package'], update_tag, env, ip.split('.')[-1]))
                        no_package.append(project)
                        boolean = False
                    else:
                        log_app.DebugLog('%s_%s: %s_%s_%s_%s found' % (project, ip, tasks[project]['servers'][ip]['update_package'], update_tag, env, ip.split('.')[-1]))
                else:
                    if not os.path.isfile('%s_%s_%s' % (tasks[project]['servers'][ip]['update_package'], update_tag, env)):
                        log_app.ErrorLog('%s_%s: %s_%s_%s not found' % (project, ip, tasks[project]['servers'][ip]['update_package'], update_tag, env))
                        no_package.append(project)
                        boolean = False
                    else:
                        log_app.DebugLog('%s_%s: %s_%s_%s found' % (project, ip, tasks[project]['servers'][ip]['update_package'], update_tag, env))
    if no_package != [] and ('--p' in options or '--p-only' in options):
        log_app.DebugLog('packaging ..')
        boolean = packaging.JavaGradle(tasks, no_package, update_tag, options, log_app, env)
        if boolean:
            log_app.DebugLog('packaging OK!')
            if not '--check' in options:
                boolean = check_package(tasks, update_tag, options, log_app, env)
        return boolean
    return boolean

def main(env, jiraid, ops_tag, update_tag, rollback_tag, tasks, act, options):
    log_app = log.WriteLog(log_options=options, log_label='app_b2b.py')
    log_app.TimeMarker()
    
#    if not '--check' in options and env == 'prod' and (act == 'update' or act == 'rollback' or act == 'stop'):
#        log_app.DebugLog('update zabbix ..')
#        zabbixHost = 'http://192.168.241.1/zabbix/api_jsonrpc.php'
#        zabbix = tzZabbix.Zabbix(zabbixHost)
#        auth = zabbix.login(username='shifeng.liang', password='Abc12345')
#        log_app.DebugLog("zabbix create maintenance: %s" % auth)
#        maintenanceid = zabbix.maintenanceCreate(ops_tag, auth)
#        log_app.DebugLog("maintenanceid: %s" % maintenanceid)
#        if zabbix.logout(auth):
#            log_app.DebugLog("logout successfully, bye")
#            log_app.DebugLog('update zabbix OK!')
#        else:
#            log_app.ErrorLog("zabbix logout error!")

    boolean = True
    lock = multiprocessing.Lock()
    threads = []

    if '--check' in options or act == 'update':
        log_app.DebugLog('check packages ..')
        boolean = check_package(tasks, update_tag, options, log_app, env)
        if boolean:
            log_app.DebugLog('check packages OK!')
            
    if '--check' in options or (boolean and not '--p-only' in options):
        try:
            for project in tasks.keys():
                start_exec = remoteExec(project, env, jiraid, ops_tag, update_tag, rollback_tag, tasks, act, options, lock)
                start_exec.start()
                threads.append(start_exec)
            for t in threads:
                t.join()
        except Exception, e:
            log_app.ErrorLog(e)
            
    log_app.CloseLog()
    
    return boolean
    
if __name__ == '__main__':
    pass
