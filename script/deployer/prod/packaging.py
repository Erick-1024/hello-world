#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import re
import subprocess
import ConfigParser

def localCmd(cmd, options, log_app):
    log_app.DebugLog(cmd)
    null = open('/dev/null', 'w')
    if not '--check' in options:
        p = subprocess.Popen(cmd, shell=True, stdout=null, stderr=subprocess.PIPE)
        retval = p.wait()
        null.close()
        if retval != 0:
            log_app.ErrorLog(p.stderr.read())
            return False
    return True

def JavaGradle(tasks, no_package, update_tag, options, log_app, env):
    gradle_tasks = []
    name_dict = {}
    packaging_cfg = ConfigParser.ConfigParser()
    packaging_cfg.read('packaging.cfg')
    gradledir = '/root/git/cana'
    git = 'cd /root/git/cana && git fetch && git checkout %s' % update_tag
    if not localCmd(git, options, log_app):
        log_app.ErrorLog(git)
        return False
    gradle_clean = 'cd /root/git/cana && gradle clean'
    if not localCmd(gradle_clean, options, log_app):
        log_app.ErrorLog(gradle_clean)
        return False
    profile = 'rmb'
    if env == 'prod':
        profile = 'nmb'
    for project in no_package:
        project_label = re.sub('-', '_', project)
        gradletask = packaging_cfg.get(project, 'gradletask')
        packagename = packaging_cfg.get(project, 'packagename')
        packagepath = packaging_cfg.get(project, 'packagepath')
        if tasks[project]['thrift_server'] == 'yes' and env == 'prod' and '--old' in options:
            for ip in tasks[project]['servers'].keys():
                confignumber = eval('confignumber_%s_%s' % (project_label, profile))
                for count in range(int(confignumber)):
                    fullname = eval('fullname_%s_%s_%s_%s' % (project_label, profile, ip.split('.')[-1], count))
                    configure = eval('configure_%s_%s_%s_%s' % (project_label, profile, ip.split('.')[-1], count))
                    f = open(fullname, 'w')
                    f.write(configure)
                    f.close()
                log_app.DebugLog(project)
                log_app.DebugLog(fullname)
                log_app.DebugLog(configure)
                #cmd = 'gradle -p %s -Pprofile=%s %s' % (gradledir, profile, gradletask)
                cmd = 'gradle -p %s %s' % (gradledir, gradletask)
                if not localCmd(cmd, options, log_app):
                    log_app.ErrorLog(cmd)
                    return False
                oldname = '%s/%s/%s' % (gradledir, packagepath, packagename)
                newname = '%s_%s_%s_%s' % (tasks[project]['servers'][ip]['update_package'], update_tag, env, ip.split('.')[-1])
                if not localCmd('mv %s %s' % (oldname, newname), options, log_app):
                    log_app.ErrorLog('mv %s %s' % (oldname, newname))
                    return False
        else:
            try:
                if packaging_cfg.get(project, 'other') == 'zyc':                
                    #cmd = 'gradle -p %s/%s -Pprofile=%s %s' % (gradledir, gradletask.split(':')[0], profile, gradletask.split(':',1)[1])
                    cmd = 'gradle -p %s/%s %s' % (gradledir, gradletask.split(':')[0], gradletask.split(':',1)[1])
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
                elif packaging_cfg.get(project, 'other') == 'xs':
                    cmd = 'gradle -p %s/%s -Pplatform=distributor %s' % (gradledir, gradletask.split(':')[0], gradletask.split(':',1)[1])
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
                elif packaging_cfg.get(project, 'other') == 'chz':
                    cmd = 'gradle -p %s clean %s:%s --refresh-dependencies -c %s/settings-order-callback-center.gradle' % (gradledir, gradletask.split(':')[0], gradletask.split(':',1)[1], gradledir)
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
                elif packaging_cfg.get(project, 'other') == 'zy':
                    cmd = 'gradle -p %s/%s -refresh-dependencies %s' % (gradledir, gradletask.split(':')[0], gradletask.split(':',1)[1])
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
                elif packaging_cfg.get(project, 'other') == 'dgx':
                    cmd = 'gradle -p %s/%s -Pprofile=product %s' % (gradledir, gradletask.split(':')[0], gradletask.split(':',1)[1])
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
                elif packaging_cfg.get(project, 'other') == 'smy':
                    cmd = 'gradle -p %s/%s %s' % (gradledir, gradletask.split(':')[0], gradletask.split(':',1)[1])
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
                elif packaging_cfg.get(project, 'other') == 'lwl':
                    cmd = 'cd /root/git/cana; gradle %s' % (gradletask)
                    if not localCmd(cmd, options, log_app):
                        log_app.ErrorLog(cmd)
                        return False
            except (ConfigParser.NoOptionError, ConfigParser.NoSectionError):
                gradle_tasks.append(gradletask)
            if '--old' in options:
                name_dict['%s/%s/%s' % (gradledir, packagepath, packagename)] = '/data/update/packages/%s_%s_%s' % (packagename, update_tag, env)
            else:
                name_dict['%s/%s/%s' % (gradledir, packagepath, packagename)] = '/data/update/packages/%s_%s' % (packagename, update_tag)

    if gradle_tasks != []:
        #cmd = 'gradle -p %s -Pprofile=%s %s' % (gradledir, profile, ' '.join(list(set(gradle_tasks))))
        cmd = 'gradle -p %s %s' % (gradledir, ' '.join(list(set(gradle_tasks))))
        if not localCmd(cmd, options, log_app):
            log_app.ErrorLog(cmd)
            return False
        
    for oldname, newname in name_dict.items():
        if not localCmd('mv %s %s' % (oldname, newname), options, log_app):
            log_app.ErrorLog('mv %s %s' % (oldname, newname))
            return False
    return True
