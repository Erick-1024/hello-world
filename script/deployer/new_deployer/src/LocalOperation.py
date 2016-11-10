
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''

import time

from OperationBase import OperationBase
from LocalCmdExecutor import call_local_cmd_no_output,call_local_cmd

class LocalOperation(OperationBase):
    '''
    实现远程操作
    '''


    def __init__(self, all_config=None, app_config=None, host_config=None, tag=None, ip=None, app_name = None, local_repository_path = None):
        super(LocalOperation, self).__init__(all_config=all_config, app_config=app_config, host_config=host_config, tag=tag, ip=ip, app_name=app_name, local_repository_path = local_repository_path)
    
    def login(self):
        ''' 登陆 '''
        self.log_success("本机部署，无需登录")
    
    def backup(self):
        '''备份本次发布的版本'''
        if not call_local_cmd_no_output(self.format_command(self.app_config["backup_command"])):
            raise Exception("执行restore_command失败")
        
        if not call_local_cmd_no_output(self.format_command("cp /tmp/{package_name} {app_backup}/{package_name}-last")):
            raise Exception("last备份失败")
    
    def restore(self):
        '''恢复到之前的版本'''
        if not call_local_cmd_no_output(self.format_command(self.app_config["restore_command"])):
            raise Exception("执行restore_command失败")
    
    def deploy_app(self):
        '''部署应用到指定目录'''
        
        if not call_local_cmd_no_output(self.format_command("cp {package_dest_full_path} /tmp/{package_name}")):
            raise Exception("last备份失败")
        
        if not call_local_cmd_no_output(self.format_command(self.app_config["remote_deploy_command"])):
            raise Exception("执行local_deploy_command失败")   
    
    def start_app(self):
        '''启动应用'''
        if not call_local_cmd_no_output(self.format_command(self.app_config["start_command"])):
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
        
            if not call_local_cmd_no_output(self.format_command(self.app_config["stop_command"])):
                raise Exception("执行stop_command失败")   
                
            time.sleep(int(self.app_config["stop_timeout"]))
    
        pids = self.fliter_pids(self.call_command_return_lines(self.format_command(self.app_config["get_pid_command"])))
        
        if pids and len(pids) > 1:
            raise Exception("该应用启动了多个进程，pids=" + pids)
        
        if pids and not call_local_cmd_no_output("kill -9 " + bytes(pids[0])):
            raise Exception("通过kill -9 强杀进程失败") 
        
        
   
    def call_command_return_lines(self, cmd):
        ''' 执行命令， 如果返回码不为0抛出异常，否则返回所有的行 '''   
        output = call_local_cmd(cmd);
        self.log_success("执行命令：" + cmd)
        lines = output.splitlines(False)
        return lines
        
    def cleanup(self):
        pass
            