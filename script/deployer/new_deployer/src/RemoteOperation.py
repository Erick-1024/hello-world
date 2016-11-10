
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''

import paramiko
import time

from OperationBase import OperationBase

class RemoteOperation(OperationBase):
    '''
    实现远程操作
    '''


    def __init__(self, all_config=None, app_config=None, host_config=None, tag=None, ip=None, app_name = None, local_repository_path = None):
        super(RemoteOperation, self).__init__(all_config=all_config, app_config=app_config, host_config=host_config, tag=tag, ip=ip, app_name=app_name, local_repository_path = local_repository_path)
    
    def login(self):
        ''' 登陆 '''
        self.log_info("开始执行ssh登陆") 
        self.client = paramiko.SSHClient()
        self.client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        self.client.connect(self.ip, port=22, username=self.host_config['login_username'], password=self.host_config['login_password'])
        self.sftp = self.client.open_sftp()
        self.log_success("ssh登陆成功")
    
    def backup(self):
        '''备份本次发布的版本'''
        if not self.check_command_statuscode(self.format_command(self.app_config["backup_command"])):
            raise Exception("执行restore_command失败")
        
        if not self.check_command_statuscode(self.format_command("cp /tmp/{package_name} {app_backup}/{package_name}-last")):
            raise Exception("last备份失败")
    
    def restore(self):
        '''恢复到之前的版本'''
        if not self.check_command_statuscode(self.format_command(self.app_config["restore_command"])):
            raise Exception("执行restore_command失败")
    
    def deploy_app(self):
        '''部署应用到指定目录'''
        
        sftp_put_command_source = self.local_repository_path + "/" + self.app_config["gradle_package_dest"]
        sftp_put_command_dest = "/tmp/" + self.get_package_name()
        self.log_info("开始执行：sftp put %s %s" % (sftp_put_command_source, sftp_put_command_dest))
        self.sftp.put(sftp_put_command_source, sftp_put_command_dest)
        self.log_success("执行成功：sftp put %s %s" % (sftp_put_command_source, sftp_put_command_dest))
        
        if not self.check_command_statuscode(self.format_command(self.app_config["remote_deploy_command"])):
            raise Exception("执行remote_deploy_command失败")   
    
    def start_app(self):
        '''启动应用'''
        if not self.check_command_statuscode(self.format_command(self.app_config["start_command"])):
            raise Exception("执行start_command失败")   
        
        time.sleep(int(self.app_config["start_timeout"]))
        
        pids = self.fliter_pids(self.call_command_return_lines(self.format_command(self.app_config["get_pid_command"])))
        
        if not pids:
            raise Exception("启动后未找到进程")
        
        if len(pids) > 1:
            raise Exception("该应用启动了多个进程，pids=" + pids)
    
    def stop_app(self):
        '''关闭应用'''
        
        if "stop_command" in self.app_config and self.app_config["stop_command"].strip():
        
            if not self.check_command_statuscode(self.format_command(self.app_config["stop_command"])):
                raise Exception("执行stop_command失败")   
                
            time.sleep(int(self.app_config["stop_timeout"]))
    
        pids = self.fliter_pids(self.call_command_return_lines(self.format_command(self.app_config["get_pid_command"])))
        
        if pids and len(pids) > 1:
            raise Exception("该应用启动了多个进程，pids=" + pids)
        
        if pids and not self.check_command_statuscode("kill -9 " + bytes(pids[0])):
            raise Exception("通过kill -9 强杀进程失败") 
        
        
    def check_command_statuscode(self, cmd):
        ''' 如果执行命令后，返回码为0返回True，否则返回false '''   
        stdin, stdout, stderr = self.client.exec_command(cmd)
        if stdout.channel.recv_exit_status() == 0:
            self.log_success("执行命令：" + cmd)
            return True
        else:
            self.log_error("执行命令：" + cmd)
            return False
        
    def call_command_return_one_line(self, cmd):
        ''' 执行命令， 如果返回码不为0抛出异常，否则返回输出的第一行 '''   
        stdin, stdout, stderr = self.client.exec_command(cmd)
        if stdout.channel.recv_exit_status() == 0:
            self.log_success("执行命令：" + cmd)
            return stdout.readline().strip()
        else:
            self.log_error("执行命令：" + cmd)
            raise Exception("执行命令失败")
    
    def call_command_return_lines(self, cmd):
        ''' 执行命令， 如果返回码不为0抛出异常，否则返回所有的行 '''   
        stdin, stdout, stderr = self.client.exec_command(cmd)
        if stdout.channel.recv_exit_status() == 0:
            self.log_success("执行命令：" + cmd)
            lines = stdout.readlines()
            if lines:
                return [ line.strip() for line in lines ]
            else: return lines;
        else:
            self.log_error("执行命令：" + cmd)
            raise Exception("执行命令失败")
        
    def cleanup(self):
        ''' 清理资源 '''
        if hasattr(self, "client") and not self.client:
            self.client.close()
            