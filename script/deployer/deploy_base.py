'''
Created on Oct 20, 2014

@author: chengwen.li
'''

import os, sys
import traceback, time
import paramiko
from progressbar import ProgressBar

tops_path = os.path.abspath(sys.argv[0] + '/../../..')
sys.path.append(tops_path + '/script/pylib')

from url_monitor import UrlMonitor
from log_monitor import LogMonitor
from deploy_logger import DeployLogger

class DeployBase:

    def __init__(self, config_dict):
        self.config = config_dict
        self.config['path.backup'] = '/data/package_backup'
        if not self.config.has_key('appname'):
            idx = self.config['path.project'].rfind('/')
            if idx < 0:
                self.config['appname'] = self.config['path.project']
            else:
                self.config['appname'] = self.config['path.project'][idx+1:]

        # init logger
        self.logger = DeployLogger(self.config['name'])

        # init ssh client
        self.client = paramiko.SSHClient()
        self.client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        self.client.connect(self.config['host'], port=22, username=self.config['username'], password=self.config['password'])
        self.sftp = self.client.open_sftp()
        self.log_monitor = None
        self.url_monitor = None

    def config_exists(self, key):
        return self.config.has_key(key) and len(self.config[key]) > 0 and not self.config[key].isspace()

    def localCmd(self, cmd):
        self.logger.info('execute local => "' + cmd + '"')
        return os.system(cmd)

    def package(self):
        gradle_package_task = ':' + self.config['path.project'].replace('/',':') + ':' + self.config['type'];
        gradle_package_task = gradle_package_task.replace('::', ':')
        if self.config['no_package']:
            return
        cmd = 'gradle -p %(tops)s' % self.config
        if not self.config['not_refresh_dependencies']:
            cmd += ' --refresh-dependencies'
        if self.config_exists('gradle.settings'):
            cmd += ' -c ' + self.config['tops'] + '/' + self.config['gradle.settings']
        for (k, v) in self.config.items():
            if k.startswith('gradle.properties.'):
                cmd += ' -P%s=%s' % (k[len('gradle.properties.'):], v)
        cmd += ' ' + self.config['gradle_args']
        if not self.config['not_clean']:
            cmd += ' clean'
        cmd += ' ' + gradle_package_task
        ret = self.localCmd(cmd)
        if ret != 0 :
            self.logger.error('gradle failed => ' + cmd)
            sys.exit(1)

    def sshExec(self, cmd):
        self.logger.info('execute remote "' + cmd + '"')
        return self.client.exec_command(cmd)

    def sshCmd(self, cmd, ignoreError = False):
        stdin, stdout, stderr = self.sshExec(cmd)  # @UnusedVariable
        line = stdout.readline()
        while len(line) > 0:
            self.logger.debug(line, False)
            line = stdout.readline()
        errInfo = stderr.read()
        if len(errInfo) > 0:
            self.logger.error('execute cmd failed => ' + errInfo)
            if not ignoreError:
                sys.exit(1)

    def sshRun(self, cmd):
        stdin, stdout, stderr = self.sshExec(cmd)  # @UnusedVariable
        if len(stderr.read()) > 0:
            sys.exit('execute cmd failed: ' + cmd)
        return stdout.read()

    def upload(self):
        find_resulsts = os.popen('find %(tops)s/%(path.project)s -name "%(appname)s*.%(type)s"' % self.config).readlines()
        if len(find_resulsts) == 0:
            self.logger.error('package[%(appname)s.%(type)s] not found.' % self.config)
            sys.exit(1)
        package_path = find_resulsts[0].strip()
        remote_dest = '/tmp/%(appname)s.%(type)s' % self.config
        self.logger.info('scp ' + package_path + ' --> ' + remote_dest)
        package_size = os.path.getsize(package_path)
        progressbar = ProgressBar(maxval=package_size).start()
        self.sftp.put(package_path, remote_dest, callback=(lambda transfered, total: progressbar.update(transfered)))
        progressbar.finish()

    def monitor_log(self):
        self.log_monitor = None
        def sftp_file_getter(path):
            return self.sftp.file(path)
        try:
            if self.config_exists('path.log'):
                self.log_monitor = LogMonitor(self.config['path.log'], self.config['monitor.duration'], sftp_file_getter, self.logger)
                self.log_monitor.start()
            else:
                self.logger.warn('no path.log specified!')
            return self.log_monitor
        except:
            self.logger.error("monitor log failed: %(path.log)s" % self.config)
            self.logger.error(traceback.format_exc())
            return None

    def monitor_url(self):
        def stop_log_monitor():
            if self.log_monitor is not None:
                self.log_monitor.stop()
        try:
            if self.config_exists('monitor.url'):
                self.url_monitor = UrlMonitor(self.config['monitor.url'], self.config['monitor.duration'], self.logger, stop_log_monitor)
                self.url_monitor.setDaemon(True)
                self.url_monitor.start()
            else:
                self.logger.warn('no monitor.url specified!')
        except:
            self.logger.error("monitor url failed: %(monitor.url)s" % self.config)
            self.logger.error(traceback.format_exc())

    def monitor(self):
        if self.config['no_monitor']:
            return
        self.monitor_log()
        self.monitor_url()
        if self.log_monitor is None and self.url_monitor is None:
            return
        while True:
            try:
                if (self.log_monitor is None or not self.log_monitor.isAlive()) and (self.url_monitor is None or not self.url_monitor.isAlive()):
                    break
                time.sleep(1)
            except KeyboardInterrupt:
                self.logger.warn('stopped manually!')
                if self.log_monitor is not None:
                    self.log_monitor.stop()
                if self.url_monitor is not None:
                    self.url_monitor.stop()
                break

