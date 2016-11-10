
#coding=utf8

'''
Created on Apr 6, 2016

@author: renshui
'''
import os,sys
import argparse 
import ConfigParser
import multiprocessing

from LocalCmdExecutor import call_local_cmd_no_output
from Logger import colorLogger, plainLogger
from Constants import Constants
from RemoteOperation import RemoteOperation
from LocalOperation import LocalOperation
from UpdateAction import UpdateAction
from StopAction import StopAction
from StartAction import StartAction
from RestartAction import RestartAction
from RollbackAction import RollbackAction

def parse_args():
    ''' 解析命令行中的参数  '''
    
    parser = argparse.ArgumentParser()
    parser.add_argument("--tag", dest="tag", required=False, default=Constants.TAG_LOCAL, help="git的tag号")
    parser.add_argument("--action", dest="action", required=True, choices=["update", "rollback", "start", "stop", "restart"], help="发布动作")
    parser.add_argument("--apps", dest="apps", required=True, help="要发布的应用名称, 应用之间使用英文的逗号分隔")
    parser.add_argument("--exclude_apps", dest="exclude_apps", required=False, help="不要发布的应用名称, 应用之间使用英文的逗号分隔")
    parser.add_argument("--config", dest="config", required=False, default="test3.cfg", help="配置文件名称")
    return parser.parse_args()

def parse_prefix_section_config(all_config = None, section_name_prefix = None):
    ''' 返回dict， key是app的名字， value是dict '''
    config = {}
    for section in all_config.sections():
        
        if(not section.startswith(section_name_prefix)):
            continue
        
        section_name = section[len(section_name_prefix):]
        config[section_name] = {}
        
        for item in all_config.items(section):
            config[section_name][item[0]] = item[1]
            
    return config 

def parse_local_repository_path(all_config = None, deploy_base_path=None):
    ''' 返回本地git仓库的路径 '''
    local_repository_path = all_config.get("git", "local_repository_path")
    
    if(os.path.isdir(local_repository_path)):
        return local_repository_path
    
    found = None
    local_repository_path = deploy_base_path
    
    while(call_local_cmd_no_output("cd %s && git status" % local_repository_path)):
        found = local_repository_path
        local_repository_path = os.path.dirname(local_repository_path)
        
    return found

    
    

def check_args(args=None, all_config=None, app_configs=None, host_configs=None, deploy_apps=None):
    ''' 校验参数正确性 '''
    
    valid = True
    
    if args.action in ['update', 'rollback'] and not args.tag:
        valid = False
        colorLogger.error("必须指定tag")
    
    for deploy_app in deploy_apps:
        app_name = parseAppName(deploy_app)
        if app_name not in app_configs:
            valid = False
            colorLogger.error("%s没有对应的配置" % app_name)
        else:
            passin_ips = parseIps(deploy_app)
            if passin_ips:
                
                all_ips = [item.strip() for item in app_configs[app_name]["ip"].split(",")]
                wrong_ips = list(set(passin_ips) - set(all_ips))
                
                if len(wrong_ips) > 0:
                    valid = False
                    colorLogger.error("%s指定的ip有误" % app_name)
            
    return valid

def parseAppName(app):
    ''' 从传入的参数中解析出appName部分，例子： 传入的参数是vbam-front-biz:192.168.1.0, 返回的结果是vbam-front-biz '''
    
    parts = app.split(":")
    
    return parts[0]

def parseIps(app):
    ''' 从传入的参数中解析出appName部分，例子： 传入的参数是vbam-front-biz:192.168.1.0, 返回的结果是['192.168.1.0'] '''
    
    parts = app.split(":")
    
    if(len(parts) == 1):
        return None
    
    return [item.strip() for item in parts[1].split("_")]
    

def package(args=None, all_config=None, app_configs=None, host_configs=None, deploy_apps=None, local_repository_path=None):
    ''' 打包 '''
    
    gradle_package_command = "cd " + local_repository_path
    
    if not args.tag == Constants.TAG_LOCAL:
        gradle_package_command += " && git fetch && git checkout " + args.tag 
    
    gradle_package_command += " && gradle clean && gradle --refresh-dependencies "

    if len(deploy_apps) == 1 and "gradle_package_option" in app_configs[parseAppName(deploy_apps[0])] and app_configs[parseAppName(deploy_apps[0])]["gradle_package_option"].strip():
        gradle_package_command += " " +  app_configs[parseAppName(deploy_apps[0])]["gradle_package_option"].strip()
    
    for deploy_app in deploy_apps:
        gradle_package_command += " " + app_configs[parseAppName(deploy_app)]["gradle_package_task"]
    
    
    plainLogger.info("开始执行打包命令:" + gradle_package_command)   
    if not call_local_cmd_no_output(gradle_package_command):
        raise Exception("打包失败")
    
def create_action_instance(args=None, all_config=None, app_config=None, host_config=None, app_name=None, ip=None, local_repository_path=None):
    '''创建单个action instance'''
    
    deployer_ip = all_config.get("deployer", "deployer_ip")
    
    if deployer_ip == ip:
        operations = LocalOperation(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, ip=ip, app_name = app_name, local_repository_path = local_repository_path)
    else:
        operations = RemoteOperation(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, ip=ip, app_name = app_name, local_repository_path = local_repository_path)
    
    if args.action == "update":
        targetObject = UpdateAction(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, operations=operations, app_name=app_name, ip=ip)
    elif args.action == "stop":
        targetObject = StopAction(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, operations=operations, app_name=app_name, ip=ip)
    elif args.action == "start":
        targetObject = StartAction(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, operations=operations, app_name=app_name, ip=ip)
    elif args.action == "restart":
        targetObject = RestartAction(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, operations=operations, app_name=app_name, ip=ip)
    elif args.action == "rollback":
        targetObject = RollbackAction(all_config=all_config, app_config=app_config, host_config=host_config, tag=args.tag, operations=operations, app_name=app_name, ip=ip)
        
    return targetObject  

def create_action_instances(args=None, all_config=None, app_configs=None, host_configs=None, deploy_apps=None, local_repository_path=None):
    '''启动处理线程'''
    
    action_instances = []
    
    for app in deploy_apps:
        app_name = parseAppName(app)
        deploy_ips = [item.strip() for item in app_configs[app_name]["ip"].split(",")]
        passin_ips = parseIps(app)
        if passin_ips:
            deploy_ips = passin_ips
            
        for ip in deploy_ips:
            action_instances.append(create_action_instance(args=args, all_config=all_config, app_config=app_configs[app_name], host_config=host_configs[ip], app_name=app_name, ip=ip, local_repository_path=local_repository_path))

    return action_instances

def mp_worker(action_instance):
    action_instance.execute()
    
if __name__ == '__main__':
    
    args = parse_args()
    deploy_base_path = os.path.abspath(os.path.dirname(sys.argv[0]))
    
    all_config = ConfigParser.ConfigParser()
    all_config.read(deploy_base_path + "/" + args.config)
    
    local_repository_path = parse_local_repository_path(all_config=all_config, deploy_base_path=deploy_base_path)
    deployer_ip = all_config.get("deployer", "deployer_ip")
    app_configs = parse_prefix_section_config(all_config = all_config, section_name_prefix="app:")
    host_configs = parse_prefix_section_config(all_config = all_config, section_name_prefix="host:")
    
    if args.apps == "all":
        deploy_apps = app_configs.keys() 
    else:
        deploy_apps = [app.strip() for app in args.apps.split(",")]
       
    exclude_apps = [] 
    if args.exclude_apps:
        exclude_apps = [app.strip() for app in args.exclude_apps.split(",")]
        
    deploy_apps = list(set(deploy_apps) - set(exclude_apps))
    
    if not check_args(args=args, all_config=all_config, app_configs=app_configs, host_configs=host_configs, deploy_apps=deploy_apps):
        sys.exit(110)
        
    if args.action == "update":
        package(args=args, all_config=all_config, app_configs=app_configs, host_configs=host_configs, deploy_apps=deploy_apps, local_repository_path=local_repository_path)
         
    process_pool = multiprocessing.Pool(5)
    process_pool.map(mp_worker, create_action_instances(args=args, all_config=all_config, app_configs=app_configs, host_configs=host_configs, deploy_apps=deploy_apps, local_repository_path=local_repository_path))
