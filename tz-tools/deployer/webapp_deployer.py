'''
Created on Oct 20, 2014

@author: chengwen.li
'''

import traceback, datetime
from deploy_base import DeployBase
from history_recorder import HistoryRecorder

class WebappDeployer(DeployBase):

    def __init__(self, config_dict, not_refresh_dependencies = False, not_clean = False, no_monitor = False, no_package = False):
        DeployBase.__init__(self, config_dict)

    def backup_war(self):
        if self.config['not_backup']:
            return
        self.config["datetimestr"] = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
        self.sshCmd('test -d %(path.backup)s || mkdir -p %(path.backup)s' % self.config)
        self.sshCmd('test -f %(path.tomcat)s/webapps/%(appname)s.war && mv %(path.tomcat)s/webapps/%(appname)s.war %(path.backup)s/%(appname)s_%(datetimestr)s.war' % self.config, True)

    def replace_war(self):
        self.sshCmd('rm -rf %(path.tomcat)s/webapps/%(appname)s*' % self.config)
        self.sshCmd('rm -rf %(path.tomcat)s/work/*' % self.config)
        self.sshCmd('cp /tmp/%(appname)s.war %(path.tomcat)s/webapps/%(appname)s.war' % self.config)

    def start_tomcat(self):
        self.sshCmd('%(path.tomcat)s/bin/startup.sh' % self.config)
        self.logger.info('==> starting tomcat ...')

    def stop_tomcat(self):
        try:
            self.sshRun("ps aux|grep %(path.tomcat)s | grep -v 'grep'| awk '{print $2}' | xargs -l kill -9" % self.config).strip()
        except:
            self.logger.warn("remote server is not started.")

    def restart(self):
        self.stop_tomcat()
        self.sshCmd('rm -rf %(path.tomcat)s/webapps/%(appname)s' % self.config)
        self.sshCmd('rm -rf %(path.tomcat)s/work/*' % self.config)
        self.start_tomcat()
        self.monitor()

    def deploy(self):
        try:
            self.package()
            self.upload()
            self.stop_tomcat()
            self.backup_war()
            self.replace_war()
            self.start_tomcat()
            self.monitor()
        except:
            self.logger.error(traceback.format_exc())

    def rollback(self):
        history_packages_str = self.sshRun('ls %(path.backup)s/%(appname)s*.war' % self.config)
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
        self.sshExec(('mv %(path.backup)/' + latest + ' /tmp/%(appname)s.war') % self.config)
        self.stop_tomcat()
        self.sshCmd('rm -rf %(path.tomcat)s/webapps/%(appname)s*' % self.config)
        self.backup_war()
        self.replace_war()
        self.start_tomcat()
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

