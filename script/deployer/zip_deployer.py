'''
Created on Oct 21, 2014

@author: chengwen.li
'''

import traceback, datetime
from deploy_base import DeployBase
from history_recorder import HistoryRecorder

class ZipDeployer(DeployBase):

    def __init__(self, config_dict, not_refresh_dependencies = False, not_clean = False, no_monitor = False, no_package = False):
        DeployBase.__init__(self, config_dict)

    def check_workdir(self):
        self.sshCmd('mkdir -p %(path.workdir)s' % self.config)
        self.sshCmd('mkdir -p %(path.backup)s' % self.config)

    def stop(self):
        self.sshCmd('test -f %(path.workdir)s/bin/%(bin)s && sh %(path.workdir)s/bin/%(bin)s stop' % self.config, True)

    def backup_zip(self):
        if self.config['not_backup']:
            return
        self.config["datetimestr"] = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
        self.sshCmd('test -d %(path.workdir)s || mkdir -p %(path.workdir)s' % self.config)
        self.sshCmd('test -f %(path.workdir)s/%(appname)s.zip && mv %(path.workdir)s/%(appname)s.zip %(path.backup)s/%(appname)s_%(datetimestr)s.zip' % self.config, True)

    def replace_zip(self):
        self.sshCmd('rm -rf %(path.workdir)s/lib' % self.config, True)
        self.sshCmd('rm -rf %(path.workdir)s/conf' % self.config, True)
        self.sshCmd('rm -rf %(path.workdir)s/bin' % self.config, True)
        self.sshCmd('mv /tmp/%(appname)s.zip %(path.workdir)s' % self.config)
        self.sshCmd('cd %(path.workdir)s && jar -xf %(appname)s.zip' % self.config)

    def start(self):
        self.sshCmd('cd %(path.workdir)s && sh bin/%(bin)s start' % self.config)

    def restart(self):
        self.stop()
        self.start()
        self.monitor()

    def deploy(self):
        try:
            self.package()
            self.upload()
            self.stop()
            self.check_workdir()
            self.backup_zip()
            self.replace_zip()
            self.start()
            self.monitor()
        except:
            self.logger.error(traceback.format_exc())

    def rollback(self):
        history_packages_str = self.sshRun('ls %(path.backup)s/%(appname)s*.zip' % self.config)
        history_packages = history_packages_str.split()
        if len(history_packages) == 0:
            self.logger.error('no history package found.')
            return None
        latest = ''
        for package in history_packages:
            if package > latest:
                latest = package
        if len(latest) == 0:
            self.logger.error('cannot find latest history pacakge.')
            return None
        idx = latest.rfind('/')
        if idx >= 0:
            latest = latest[idx+1:]
        self.logger.info('rollback to ' + latest)
        self.sshExec(('mv %(path.backup)s/' + latest + ' /tmp/%(appname)s.zip') % self.config)
        self.stop()
        self.sshCmd('rm -rf %(path.workdir)s/*.zip' % self.config, True)
        self.backup_zip()
        self.replace_zip()
        self.start()
        self.monitor()
        return latest

    def execute(self):
        history_recorder = HistoryRecorder(self.sftp.file(self.config['path.history'], mode='a'))
        task =self.config['task']
        if task == 'deploy':
            self.deploy()
            history_recorder.record_deploy(self.config['appname'])
        elif task == 'restart':
            self.restart()
            history_recorder.record_restart(self.config['appname'])
        elif task == 'rollback':
            rolled_back_target = self.rollback()
            if rolled_back_target is not None:
                history_recorder.record_rollback(rolled_back_target)
        else:
            self.logger.error('task:%s is illegal, run deploy' % (task))
            self.deploy()

