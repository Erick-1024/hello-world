
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''
from abc import ABCMeta, abstractmethod
from Logger import colorLogger, plainLogger
from Constants import Constants
from Util import is_positive_int

class OperationBase(object):
    '''
    所有operation的基类
    '''
    __metaclass__ = ABCMeta


    def __init__(self, all_config=None, app_config=None, host_config=None, tag=None, ip=None, app_name=None, local_repository_path = None):
        self.all_config = all_config
        self.app_config = app_config
        self.host_config = host_config
        self.tag = tag
        self.ip = ip
        self.app_name = app_name
        self.local_repository_path = local_repository_path
    
    @abstractmethod
    def login(self):
        ''' 登陆 '''
        pass
    
    @abstractmethod
    def backup(self):
        '''备份本次发布的版本'''
        pass    
    
    @abstractmethod
    def restore(self):
        '''恢复到之前的版本'''
        pass
    
    @abstractmethod
    def deploy_app(self):
        '''部署应用到指定目录'''
        pass    
    
    @abstractmethod
    def start_app(self):
        '''启动应用'''
        pass
    
    @abstractmethod
    def stop_app(self):
        '''关闭应用'''
        pass
    
    @abstractmethod
    def cleanup(self):
        '''关闭应用'''
        pass
    
    def get_package_name(self):
        ''' 获取包名,如：vbam-front-biz.war '''
        return self.app_config["gradle_package_dest"].split("/")[-1]
    
    def get_package_name_no_suffix(self):
        ''' 获取包名,如：vbam-front-biz '''
        package_name = self.get_package_name()
        index = package_name.rfind(".")
        if index == -1:
            return package_name
        else:
            return package_name[:index]
    
    def log_error(self, msg):
        colorLogger.error("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def log_success(self, msg):
        colorLogger.info("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def log_warn(self, msg):
        colorLogger.warn("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def log_info(self, msg):
        plainLogger.info("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def format_command(self, command, **kwargs):
        my_dict = {}
        my_dict["app_backup"] = Constants.APP_BACKUP_DIR
        my_dict["package_name"] = self.get_package_name()
        my_dict["package_name_no_suffix"] = self.get_package_name_no_suffix()
        my_dict["tag"] = self.tag
        my_dict["package_dest_full_path"] = self.local_repository_path + "/" + self.app_config["gradle_package_dest"]
        
        for key, value in kwargs.iteritems():
            my_dict[key] = value
        
        return command.format(**my_dict)
    
    def fliter_pids(self, pids):
        ''' 过滤出真正的pid '''
        if not pids:
            return pids;
        else:
            return [ int(pid) for pid in pids if  is_positive_int(pid)]