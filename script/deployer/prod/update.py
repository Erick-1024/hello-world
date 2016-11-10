#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import os
import sys
import re
import workflow
import init
import log
import proto_b2b
import app_b2b

def HELP():
    os.system('clear')
    print('\n')
    print('%s' % Output_symbol(position='middle', string='Only lazy people can change the world!\n'))
    help = [
            '=',
            'Command:',
#            '\tpython update.py JiraID Project:IP:Number',
            '-',
            'Options:',
            '\t--action=update|stop|start|restart|rollback',
            '\tdefault: --action=update',
            '',
            '\t--tag=R.+|last',
            '\tdefault: --tag=last',
            '',
            '\t--p --p-only --check ',
            '-',
            'Example:',
            '\tpython update.py tops-member-server:192.168.250.18:1,192.168.250.19 tops-front-operator-member',
            '\t./update.py --tag= --force=beta tops-member-server:192.168.250.18 --action=update',
#            '\t./update.py  ',
            '='
            ]
    for help_value in help:
        try:
            if help_value == '=' or help_value == '-':
                print('\033[1;32m%s\033[0m' % Output_symbol(help_value))
            else:
                print('%s' % help_value)
        except IndexError:
            pass
    print('%s' % Output_symbol(position='right', string='Version : 1.2'))
    
def Output_symbol(filler=' ', position='', string=''):
    try:
        size = int(os.popen('./resize').readline().split('=')[1].split(';')[0])
    except IndexError:
        size = 0
    if position.lower() == 'middle':
        str_length = len(string)
        return '%s%s' % (filler * ((size - str_length ) / 2), string)
    elif position.lower() == 'right':
        str_length = len(string)
        return '%s%s' % (filler * (size - str_length), string)
    return filler * size

def check_argv(parameters):
    jiraid, projects, act, options, tag = '', [], '', [], ''
    parameters_tmp = parameters[:]
    projects_tmp = []
    for parameter in parameters:
        if re.match('^NBB-[0-9]+$', parameter.upper()) and jiraid == '':
            jiraid = parameter.upper()
            parameters_tmp.remove(parameter)
        elif re.match('^--action=(update|stop|start|restart|offline|online|rollback|test)$', parameter.lower()) and act == '':
            act = parameter.lower().split('=')[1]
            parameters_tmp.remove(parameter)
        elif '--force=beta' == parameter.lower() and not '--force=prod' in options:
            options.append(parameter.lower())
            parameters_tmp.remove(parameter)
        elif '--force=prod' == parameter.lower() and not '--force=beta' in options:
            options.append(parameter.lower())
            parameters_tmp.remove(parameter)
        elif re.match('--(p|p-only|check|old|noupdatejira|noupdateops)', parameter.lower()):
            options.append(parameter.lower())
            parameters_tmp.remove(parameter)
        elif re.match('^--tag=(.+|last)$', parameter.lower()) and tag == '':
            tag = parameter.split('=')[1]
            parameters_tmp.remove(parameter)
        else:
            #if re.match('^[a-z]+(-[a-z]+2?[a-z]+){1,}(:192\.168\.[0-9]{3}\.[0-9]{1,2}(:[0-9]+)?(,192\.168\.[0-9]{3}\.[0-9]{1,2}(:[0-9]+)?)*)?$', parameter.lower()) and not parameter.lower().split(':')[0] in projects_tmp:
            if re.match('^.*(:192\.168\.[0-9]{3}\.[0-9]{1,2}(:[0-9]+)?(,192\.168\.[0-9]{3}\.[0-9]{1,2}(:[0-9]+)?)*)?$', parameter.lower()) and not parameter.lower().split(':')[0] in projects_tmp:
            #if re.match('[a-z]+(-[a-z]+){1,}(:[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+(:[0-9]+)?(,[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+(:[0-9]+)?)*)?$', parameter.lower()) and not parameter.lower().split(':')[0] in projects_tmp:
                projects.append(parameter)
                projects_tmp.append(parameter.split(':')[0])
                parameters_tmp.remove(parameter)
                
    if act == '':
        act = 'update'
    if act == 'rollback' and tag == '':
        tag = 'last'
    if ('--force=beta' in options or '--force=prod' in options) and jiraid == '':
        jiraid = 'NBB-nonumber'
    if ('--force=beta' in options or '--force=prod' in options) and act != 'update' and act != 'rollback' and tag == '':
        tag = 'notag'
        
    if not '--force=beta' in options and not '--force=prod' in options and jiraid == '':
        parameters_tmp.append(u'普通模式下的任何操作，JiraID都不能为空')
        
    if ('--force=beta' in options or '--force=prod' in options) and act == 'update' and (projects == [] or tag == ''):
        parameters_tmp.append(u'强制模式下的update操作，project和tag不能为空')
        
    if ('--force=beta' in options or '--force=prod' in options) and act != 'update' and projects == []:
        parameters_tmp.append(u'强制模式下update以外的操作，project不能为空')
        
    if 'proto-b2b' in [project.split('=')[0] for project in projects] and ['proto-b2b'] != [project.split('=')[0] for project in projects]:
        parameters_tmp.append(u'proto-b2b不是唯一')        

    if not parameters_tmp == []:
        HELP()
        print '\033[1;31mErrors:\033[0m'
        for error in parameters_tmp:
            print '\tparameter error: %s' % error
        return False
    return jiraid, projects, act, options, tag

def main():
    parameters = sys.argv[1:]
    parameters_clean = check_argv(parameters)
    if not parameters_clean:
        print
        sys.exit(1)
    argv_jiraid, argv_projects, argv_act, options, argv_tag = parameters_clean    
    
    os.system('clear')
    log_update = log.WriteLog(options)
    log_update.TimeMarker()
    log_update.DebugLog('check argv OK!')
    log_update.DebugLog('get issues ..')
    tzjira = workflow.tzJira()

    env, jiraid, ops_tag, update_tag, rollback_tag, projects, debugs, errors = tzjira.Get_issues(argv_jiraid, argv_projects, argv_act, options, argv_tag)
    if debugs != []:
        for debug in debugs:
            log_update.DebugLog(debug)
    if errors != []:
        for error in errors:
            log_update.ErrorLog(error)
        log_update.CloseLog()
        sys.exit(1)
    log_update.DebugLog('get issues OK!')
    
    log_update.DebugLog('init issues .. ')
    tasks, debugs, errors = init.main(env, projects)
    if debugs != []:
        for debug in debugs:
            log_update.DebugLog(debug)
    if errors != []:
        for error in errors:
            log_update.ErrorLog(error)
        log_update.CloseLog()
        sys.exit(1)
    log_update.DebugLog('init issues OK!')
    
    if not '--check' in options and 'update' == argv_act and not '--force=beta' in options and not '--force=prod' in options and env == 'prod' and not '--noupdatejira' in  options:
        log_update.DebugLog('update jira ..')
        tzjira.Update_issues(jiraid, 'prodstart')
        log_update.DebugLog('update jira OK!')
    
    log_update.DebugLog('tasks begin ..')    
    task_type = tasks.pop('type')
    if task_type == 'static' and argv_act == 'update':
        boolean = proto_b2b.syncing(jiraid, ops_tag, update_tag, tasks, options).main()
    elif task_type == 'application':
        boolean = app_b2b.main(env, jiraid, ops_tag, update_tag, rollback_tag, tasks, argv_act, options)    
    log_update.DebugLog('tasks finish OK!')
    
    if not '--check' in options and 'update' == argv_act and not '--force=beta' in options and not '--force=prod' in options and boolean and not '--p-only' in options and not '--noupdatejira' in  options:
        log_update.DebugLog('update jira ..')
        if env == 'beta':
            tzjira.Update_issues(jiraid, 'betaend')
        elif env == 'prod':
            tzjira.Update_issues(jiraid, 'prodend')
        log_update.DebugLog('update jira OK!')
    log_update.CloseLog()
    
if __name__ == '__main__':
    main()
