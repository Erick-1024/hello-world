
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''
from ActionBase import ActionBase

class StopAction(ActionBase):
    ''' 关闭 '''
    def __init__(self, all_config=None, app_config=None, host_config=None, tag=None, operations=None, app_name=None, ip=None):
        super(StopAction, self).__init__(all_config=all_config, app_config=app_config, host_config=host_config, tag=tag, app_name=app_name, ip=ip)
        self.operations = operations
        
    def do_execute(self):
        self.operations.login()
        self.operations.stop_app()
        
    def cleanup(self):
        self.operations.cleanup()
        