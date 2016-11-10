#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import json
import base64
import urllib
import urllib2

class tzJira(object):
    def __init__(self, username='tianjun.zhu', password='tian0314'):
        self.username = username
        self.password = password
        self.transition = {
                      "prodstart":81,
                      "prodend":411,
                      "betaend":611
                      }

    def Get_issues(self, argv_jiraid = 'NBB-nonumber', argv_projects = 'tops-fornt-jira', argv_act = 'update', options = '--force=beta', argv_tag = 'v_1') :
        env, update_tag, projects = '', '', []
        debugs, errors = [], []
        jiraid = argv_jiraid
        rollback_tag = argv_tag
        if '--force=beta' in options:
            env, update_tag, projects = 'beta', argv_tag, argv_projects
        elif '--force=prod' in options:
            env, update_tag, projects = 'prod', argv_tag, argv_projects
        else:
            try:
                debugs.append('Connect Jira ..')
                issues = {
                          'beta':json.loads(urllib.urlopen('http://%s:%s@jira.travelzen.cn/rest/api/latest/search?jql=project = NBB AND status = "发布_待发布到Beta环境" ORDER BY issue' % (self.username, self.password)).read().decode('utf8')),
                          'prod':json.loads(urllib.urlopen('http://%s:%s@jira.travelzen.cn/rest/api/latest/search?jql=project = NBB AND status = " 发布_待发布到生产" ORDER BY priority DESC' % (self.username, self.password)).read().decode('utf8'))
                          }
                debugs.append('Connect Jira OK!')
                for jira_env in issues.keys():
                    if issues[jira_env]['total'] > 0:
                        for issue in issues[jira_env]['issues']:
                            if issue['key'] == argv_jiraid:
                                try:
                                    update_tag = issue['fields']['customfield_10400'].strip()
                                except AttributeError as e:
                                    errors.append(e)
                                    continue
                                try:
                                    jira_projects = [str(project['value']).strip() for project in issue['fields']['customfield_10600']]
                                except AttributeError as e:
                                    errors.append(e)
                                    continue
                                
                                if argv_projects != []:
                                    for argv_project in argv_projects:
                                        if not argv_project.split(':')[0] in jira_projects:
                                            errors.append('%s: not found %s' % (argv_jiraid, argv_project.split(':')[0]))
                                    projects = argv_projects
                                else:
                                    if 'proto-b2b' in jira_projects and ['proto-b2b'] != jira_projects:
                                        errors.append('%s: proto-b2b is not only' % argv_jiraid)
                                        continue
                                    projects = jira_projects
                                env = jira_env
                                break
            except IOError as e:
                errors.append('Jira: %s' % e)
                
        if argv_act == 'update':
            ops_tag= '%s_%s' % (argv_act, update_tag)
        else:
            ops_tag = '%s_%s' % (argv_act, argv_tag)
        
        if ['proto-b2b'] == projects and argv_act != 'update':
            errors.append('proto-b2b: can only be updated')
        
        if projects == [] or update_tag == '' or env == '':
            errors.append('%s does not exist' % argv_jiraid)
        return env, jiraid, ops_tag, update_tag, rollback_tag, projects, debugs, errors
    
    def Update_issues(self, jiraid, update):
        rawData = {
                   "transition":{
                                 "id":self.transition[update]
                                 }
                   }
        requestData = json.dumps(rawData)
        url = 'http://jira.travelzen.cn/rest/api/2/issue/%s/transitions' % (jiraid)
        base64string = base64.encodestring('%s:%s' % (self.username, self.password))[:-1]
        authheader =  "Basic %s" % base64string
        req = urllib2.Request(url)
        req.add_header("Authorization", authheader)
        req.add_header("Content-Type", 'application/json')
        responseData = urllib2.urlopen(req, data=requestData)
        if responseData.getcode() != 204:
            return "%s: %s" % (jiraid, responseData.read())
        return False

if __name__ == '__main__':
    f=tzJira()
    print f.Get_issues()
