
#coding=utf8

'''
Created on Apr 30, 2016

@author: renshui
'''
import subprocess
from Logger import colorLogger, plainLogger

def call_local_cmd_no_output(cmd=None):
    ''' 执行本地命令, 如果执行成功返回True, 否则放回false'''
    null = open('/dev/null', 'w')
    try:
        subprocess.check_call(cmd, stdout=null, stderr=null, shell=True)
    except:
        colorLogger.error("执行命令失败:" + cmd);
        return False
    else:
        colorLogger.info("执行命令成功:" + cmd);
        return True
    finally:
        null.close()
    
def call_local_cmd(cmd=None):
    ''' 执行本地命令, 返回结果'''
    return subprocess.check_output(cmd, shell=True)
