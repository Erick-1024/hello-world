
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''
from abc import ABCMeta, abstractmethod

from Logger import colorLogger, plainLogger
import traceback
from multiprocessing import Lock,Manager

class ActionBase(object):
    '''
    所有action的基类
    '''
    __metaclass__ = ABCMeta
    
    locks = {}
    
    manager = Manager()


    def __init__(self, all_config=None, app_config=None, host_config=None, tag=None, app_name=None, ip=None):
        self.all_config = all_config
        self.app_config = app_config
        self.host_config = host_config
        self.tag = tag
        self.app_name = app_name
        self.ip = ip
        if not self.app_name in ActionBase.locks:
            self.lock = ActionBase.manager.Lock()
            ActionBase.locks[self.app_name] = self.lock
        else: self.lock = ActionBase.locks[self.app_name]
    
    def execute(self):
        try:
            self.lock.acquire()
            self.do_execute()
        except Exception as e:
            self.log_error(e.message + "\n" + traceback.format_exc())
        finally:
            self.cleanup()
            self.lock.release()
    
    @abstractmethod
    def do_execute(self):
        pass
        
    @abstractmethod
    def cleanup(self):
        pass
    
    def log_error(self, msg):
        colorLogger.error("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def log_success(self, msg):
        colorLogger.info("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def log_warn(self, msg):
        colorLogger.warn("[%s-%s]:%s" % (self.app_name, self.ip, msg))
        
    def log_info(self, msg):
        plainLogger.info("[%s-%s]:%s" % (self.app_name, self.ip, msg))