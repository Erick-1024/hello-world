#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import re
import ConfigParser

def main(env, argv_projects):
    debugs, errors = [], []
    init = ConfigParser.ConfigParser()
    init.read("init.cfg")
    if ['proto-b2b'] == [argv_project.split(':')[0] for argv_project in argv_projects]:
        debugs.append('init proto-b2b ..')
        try:
            tasks_clean_up = {
                             'type':'static',
                             'git':init.get('static', 'git'),
                             'rsync':init.get('static', 'rsync_%s' % env),
                             'debug_log':init.get('other', 'debug_log'),
                             'error_log':init.get('other', 'error_log')
                             }
        except (ConfigParser.NoOptionError, ConfigParser.NoSectionError) as e:
            errors.append(e)
        debugs.append('init proto-b2b OK!')
    elif ['proto-scm'] == [argv_project.split(':')[0] for argv_project in argv_projects]:
        debugs.append('init proto-scm ..')
        try:
            tasks_clean_up = {
                             'type':'static',
                             'git':init.get('scm', 'git'),
                             'rsync':init.get('scm', 'rsync_%s' % env),
                             'debug_log':init.get('other', 'debug_log'),
                             'error_log':init.get('other', 'error_log')
                             }
        except (ConfigParser.NoOptionError, ConfigParser.NoSectionError) as e:
            errors.append(e)
        debugs.append('init proto-scm OK!')
    else:
        debugs.append('init other app .. %s' % argv_projects)
        count = {}
        for project_key in argv_projects:
            count[project_key.split(':', 1)[0]] = {}
            try:
                for ip_key in project_key.split(':', 1)[1].split(','):
                    try:
                        count[project_key.split(':', 1)[0]][ip_key.split(':')[0]] = list(set([number for number in ip_key.split(':')[1]]))
                    except IndexError:
                        pass
            except IndexError:
                pass
        tasks_clean_up = {}
        argv_projects_tmp = []
        for argv_project in argv_projects:
            try:
                argv_project.split(':')[1]
                argv_projects_tmp.append(argv_project)
            except IndexError:
                try:
                    argv_projects_tmp.append('%s:%s' % (argv_project, init.get('application', re.sub('-', '_', argv_project))))
                except ConfigParser.NoOptionError as e:
                    errors.append(e)
        tasks = {}
        for project_tmp in argv_projects_tmp:
            project = project_tmp.split(':', 1)[0]
            tasks[project] = {}
            if not re.match('^[a-z]+(-[a-z]+){1,}$', project_tmp):
                try:
                    for ip in project_tmp.split(':', 1)[1].split(','):
                        #if re.match('^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$', ip):
                        if re.match('^192\.168\.[0-9]{3}\.[0-9]{1,3}$', ip):
                            tasks[project][ip] = {'1':{}}
                        #elif re.match('^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}:[0-9]+$', ip):
                        elif re.match('^192\.168\.[0-9]{3}\.[0-9]{1,3}:[0-9]+$', ip):
                            tasks[project][ip.split(':' ,1)[0]] = {number:{} for number in ip.split(':' ,1)[1]}
                except IndexError:
                    pass
            else:
                errors.append('error in 61 line')
        
        try:
            default_packing_method = init.get('application', 'default_packing_method')
            all_packing_methods = init.get('application', 'all_packing_methods').split(',')
        except (ConfigParser.NoSectionError, ConfigParser.NoOptionError) as e:
            errors.append(e)
            return tasks, errors
        example = {}
        for project in tasks.keys():
            try:
                servers = {}
                other = {}
                project_label = re.sub('-', '_', project)
                for ip in set(set(tasks[project].keys()).intersection(init.get('application', '%s_pools' % env).split(','))).intersection((init.get('application', '%s' % project_label).split(','))):
                    servers[ip] = {war_key:war_value for war_key, war_value in init.items(default_packing_method)}
                    for packing_method in all_packing_methods:
                        if packing_method != default_packing_method:
                            if project_label in init.get('application', '%s_packages' % packing_method).split(','):
                                try:
                                    for packing_method_key, packing_method_value in init.items(packing_method):
                                        try:
                                            servers[ip][packing_method_key] = packing_method_value
                                        except (ConfigParser.NoSectionError, ConfigParser.NoOptionError) as e:
                                            errors.append(e)
                                except ConfigParser.NoSectionError:
                                        pass
                            
                        for packing_method_label in ['%s_%s' % (packing_method, project_label), '%s_%s_%s' % (packing_method, project_label, ip)]:
                            try:
                                for packing_method_label_key, packing_method_label_value in init.items(packing_method_label):
                                    try:
                                        servers[ip][packing_method_label_key] = packing_method_label_value
                                    except (ConfigParser.NoSectionError, ConfigParser.NoOptionError) as e:
                                        errors.append(e)
                            except (ConfigParser.NoSectionError, ConfigParser.NoOptionError):
                                pass
                    try:
                        try:
                            servers[ip]['java_path'] = ','.join([servers[ip]['java_path'].split(',')[int(number)-1] for number in  count[project][ip]])
                        except IndexError as e:
                            errors.append('%s: 112 line' % e)
                    except KeyError:
                        pass
                    other[ip] = {other_key:other_value for other_key, other_value in init.items('other')}
                    try:
                        for other_ip_key, other_ip_value in init.items('other_%s' % ip):
                            try:
                                other[ip][other_ip_key] = other_ip_value
                            except (ConfigParser.NoSectionError, ConfigParser.NoOptionError) as e:
                                errors.append(e)
                    except ConfigParser.NoSectionError:
                        pass
            except (ConfigParser.NoSectionError, ConfigParser.NoOptionError) as e:
                errors.append(e)
            example[project] = {'other':other, 'servers':servers}

        tasks_clean_up = example
        for project_clean_up in tasks_clean_up.keys():
            for servers_ip_clean_up in tasks_clean_up[project_clean_up]['servers'].keys():
                while re.match('.*#__.+_.+__#.*', ' '.join(tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up].values())):
                    for servers_ip_key in tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up].keys():
                        for servers_ip_key_tmp in tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up].keys():
                            if not re.match('.*#__.+_.+__#.*', tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key_tmp]):
                                tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key] = re.sub('#__%s__#' % servers_ip_key_tmp, tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key_tmp], tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key])
                            tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key] = re.sub('#__project_mark__#', project_clean_up, tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key])
                            tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key] = re.sub('#__package_path__#', example[project_clean_up]['other'][servers_ip_clean_up]['package_path'], tasks_clean_up[project_clean_up]['servers'][servers_ip_clean_up][servers_ip_key])
        for project in tasks_clean_up.keys():
            if project in init.get('application', 'thrift_server'):
                tasks_clean_up[project]['thrift_server'] = 'yes'
            else:
                tasks_clean_up[project]['thrift_server'] = 'no'
            if tasks_clean_up[project]['servers'] == {}:
                errors.append('%s: Node does not exist in %s: %s' % (project, env, ' '.join(tasks[project].keys())))
            try:
                tasks_clean_up[project]['offline'] = init.get('%s_offline' % env, '%s' % re.sub('-', '_', project))
            except (ConfigParser.NoSectionError, TypeError, ConfigParser.NoOptionError):
                tasks_clean_up[project]['offline'] = ''
        tasks_clean_up['type'] = 'application'
        debugs.append('init other app OK!')
    return tasks_clean_up, debugs, errors

if __name__ == '__main__':
    pass
