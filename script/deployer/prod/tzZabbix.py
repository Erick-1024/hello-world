#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu
# mail: jitao.chu@travelzen.com

import json
import urllib2
import getpass
import os

class JsonEncoder(object):
    def __init__(self):
        pass
    def login(self, username='shifeng.liang', password='Abc12345'):
        login_request = {
           "jsonrpc": "2.0",
           "method": "user.login",
           "params": {
              "user": username,
              "password": password
           },
           "id": 1
        }
        return json.dumps(login_request)
        
    def logout(self, auth):
        logout_request = {
            "jsonrpc": "2.0",
            "method": "user.logout",
            "params": [],
            "id": 1,
            "auth": auth
        }
        return json.dumps(logout_request)
        
    def maintenanceCreate(self, name, auth):
        today = int(os.popen('date +"%s"').read())
        tommorrow = today + 86400
        maintenanceCreate = {
            "jsonrpc": "2.0",
            "method": "maintenance.create",
            "params": {
                "active_since":today,
                "active_till":tommorrow,
                "name": name,
                "groupids": [
                    "2",
                    "14"
                ],
            "timeperiods": [
                {
                    "timeperiod_type": 0
                }
            ]
            },
            "auth": auth,
            "id": 1
        }
        return json.dumps(maintenanceCreate)

    def maintenanceExists(self, name, auth):
        maintenanceExists = {
            "jsonrpc": "2.0",
            "method": "maintenance.exists",
            "params": {
                "name": name,
            },
            "auth": auth,
            "id": 1
        }
        return json.dumps(maintenanceExists)

    def maintenanceUpdate(self):
        pass

class JsonDecoder(object):
    def __init__(self, data):
        self.data = json.loads(data)
        if self.data.has_key('error'):
            raise Exception(self.data['error']['message'])

    def login(self):
        return self.data['result']

    def logout(self):
        return self.data['result']

    def maintenanceCreate(self):
        return self.data['result']['maintenanceids']

    def maintenanceExists(self):
        return self.data['result']

class Zabbix(object):
    def __init__(self, zabbixHost):
        self.zabbixHost = zabbixHost
        
    def __HttpRequest(self, requestData):
        req = urllib2.Request(self.zabbixHost, data=requestData)
        req.add_header('Content-Type', 'application/json')
        return urllib2.urlopen(req)

    def login(self, username, password):
        requestData = JsonEncoder().login(username, password)
        responseData = self.__HttpRequest(requestData)
        print requestData
        return JsonDecoder(responseData.read()).login()
        
    def logout(self, auth):
        requestData = JsonEncoder().logout(auth)
        responseData = self.__HttpRequest(requestData)
        return JsonDecoder(responseData.read()).logout()

    def maintenanceCreate(self, name, auth):
        requestData = JsonEncoder().maintenanceExists(name, auth)
        responseData = self.__HttpRequest(requestData)
        if not JsonDecoder(responseData.read()).maintenanceExists():
            requestData = JsonEncoder().maintenanceCreate(name, auth)
            print requestData
            responseData = self.__HttpRequest(requestData)
            return JsonDecoder(responseData.read()).maintenanceCreate()
        else:
            return "%s already exists" % name

def unittest():
    zabbixHost = 'http://192.168.241.1/zabbix/api_jsonrpc.php'
    username = 'shifeng.liang'
    password = 'Abc12345'
    zabbix = Zabbix(zabbixHost)
    auth = zabbix.login(username, password)
#    print "认证信息: %s".decode('utf8') % auth
    try:
        maintenanceid = zabbix.maintenanceCreate('test', auth)
        print "maintenanceid: %s" % maintenanceid
    finally:
        if zabbix.logout(auth):
            print "logout successfully, bye"

if __name__ == '__main__':
    unittest()
