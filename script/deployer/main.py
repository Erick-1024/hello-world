#!/usr/bin/env python2
#coding=utf8
'''
Created on Oct 17, 2014

@author: chengwen.li
'''

import os, sys
import argparse, ConfigParser, commands

from webapp_deployer import WebappDeployer
from zip_deployer import ZipDeployer

DEFAULT = 'default'
TASK_DELIMITER = '@'
CUSTOM_SETTINGS_NAME = 'my_deploy.ini'
parser = argparse.ArgumentParser()
parser.add_argument("-s", "--settings",
        dest="settings", metavar="path", required=False,
        help="部署配置文件的路径")
parser.add_argument("-nc", "--not-clean",
        dest="not_clean", action='store_true', default=False,
        help="项目打包前不进行clean，默认进行")
parser.add_argument("-nrd", "--not-refresh-dependencies",
        dest="not_refresh_dependencies", action='store_true', default=False,
        help="项目打包前不刷新依赖，默认刷新")
parser.add_argument("-nm", "--no-monitor",
        dest="no_monitor", action='store_true', default=False,
        help="不监视tomcat启动过程，默认监视")
parser.add_argument("-np", "--no-package",
        dest="no_package", action='store_true', default=False,
        help="不打包目标项目，使用现有的包")
parser.add_argument("-nb", "--not-backup",
        dest="not_backup", action='store_true', default=False,
        help="不备份服务器上原有的包")
parser.add_argument("-m", "--module",
        dest="modules", action="append", metavar="module" + TASK_DELIMITER + "task",
        help="目标模块名，即配置文件里方括号里的字，可以指定多个，如：-m xxx -m yyy。模块名可以接任务名，如：-m xxx" + TASK_DELIMITER + "restart。任务可以是：deploy（默认任务）、restart、rollback，不指定时执行deploy任务")
parser.add_argument("-g", "--gradle-args",
        dest="gradle_args", default='',
        help="执行打包命令时用到的gradle参数，请用引号括起来")

if __name__ == '__main__':

    args = parser.parse_args()
    tops_path = os.path.abspath(sys.argv[0] + '/../../..')

    # if setttings file is not specified, try default or guess it
    if args.settings is None:
        if os.path.exists(tops_path + '/script/deployer/settings/' + CUSTOM_SETTINGS_NAME):
            args.settings = tops_path + '/script/deployer/settings/' + CUSTOM_SETTINGS_NAME
        else:
            results = commands.getstatusoutput("git -C %s branch | grep '*' | cut -d ' ' -f 2,2" % (tops_path))
            if results[0] != 0:
                sys.exit('deploy settings file missing.')
            else:
                branch_name = results[1]
                branch_settings_mapping_path = os.getenv('HOME') + '/.tz_deployer'
                settings_name = ''
                if os.path.exists(branch_settings_mapping_path):
                    branch_settings_mapping = open(branch_settings_mapping_path)
                    for line in branch_settings_mapping.readlines():
                        if line.startswith(branch_name + '='):
                            settings_name = line.strip()[len(branch_name + '='):]
                if len(settings_name) == 0:
                    if branch_name == 'master' or branch_name == 'work':
                        settings_name = 'test_1.ini'
                    if branch_name.find('release') >= 0:
                        settings_name = 'test_3.ini'
                if len(settings_name) == 0:
                    sys.exit('deploy settings file missing.')
                else:
                    args.settings = tops_path + '/script/deployer/settings/' + settings_name

    print 'using settings file =>', args.settings

    config = ConfigParser.ConfigParser()
    config.read(args.settings)

    # prepare module task
    target_module_task = {}
    for arg_module in args.modules:
        idx = arg_module.rfind(TASK_DELIMITER)
        module = arg_module
        task = 'deploy'
        if idx > 0:
            module = arg_module[0:idx]
            task = arg_module[idx+1:]
        target_module_task[module] = task

    # prepare module config
    default_config = {}
    default_config_items = config.items(DEFAULT)
    for config_item in default_config_items:
        default_config[config_item[0]] = config_item[1]
    defined_modules = config.sections()
    modules_config_dict = {}
    for module_name in target_module_task.keys():
        if module_name not in defined_modules:
            print '[%s] not defined in %s' % (module_name, args.settings)
            continue
        module_config = default_config.copy()
        for config_item in config.items(module_name):
            module_config[config_item[0]] = config_item[1]
        module_config['name'] = module_name
        module_config['tops'] = tops_path
        module_config['task'] = target_module_task[module_name]
        module_config['not_refresh_dependencies'] = args.not_refresh_dependencies
        module_config['not_clean'] = args.not_clean
        module_config['no_monitor'] = args.no_monitor
        module_config['no_package'] = args.no_package
        module_config['not_backup'] = args.not_backup
        module_config['gradle_args'] = args.gradle_args
        if module_config.has_key('type') and module_config['type'] == 'zip':
            module_config['type'] = 'zip'
        else:
            module_config['type'] = 'war'
        modules_config_dict[module_name] = module_config

    # run
    for module_name in modules_config_dict.keys():
        module_config = modules_config_dict[module_name]
        if module_config['type'] == 'zip':
            module_deployer = ZipDeployer(module_config)
            module_deployer.execute()
        else:
            module_deployer = WebappDeployer(module_config)
            module_deployer.execute()


