#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import paramiko

class sshClient(object):
    def __init__(self, host, port=22, method='password', user='root', key_filename=None, password='123456'):
        self.host = host
        self.port = port
        self.method = method
        self.user = user
        self.key_filename = key_filename
        self.password = password

    def connect(self):
        ssh = paramiko.SSHClient()
        ssh.load_system_host_keys()
        ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
#        if self.host == '192.168.192.100':
#	    ssh.connect(port=11211, method='pkey', user=cana, password=self.password, timeout=10)
#            scp = ssh.open_sftp()
#            return ssh, scp
        if self.method == 'pkey':
            ssh.connect(hostname=self.host, port=self.port, username=self.user, key_filename=self.key_filename, timeout=10)
            scp = ssh.open_sftp()
            return ssh, scp
        if self.method == 'password':
            ssh.connect(self.host, port=self.port, username=self.user, password=self.password, timeout=10)
            scp = ssh.open_sftp()
            return ssh, scp
            
if __name__ == '__main__':
    pass
