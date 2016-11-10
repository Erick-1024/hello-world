#!/usr/bin/env python2
# -*- coding: utf-8 -*-
# author: chengwen.li
import os, sys, traceback, re, logging
import os.path as path
import zk_client
import colorful_printer as printer
#import pdb

logging.basicConfig()

ZK_PROPERTIES = "global/properties/zkService.properties"

def find_zk_config(start_point):
    target = start_point
    if (not path.exists(target)) or path.isfile(target):
        target = path.dirname(target)
    while not path.exists(path.join(target, ZK_PROPERTIES)):
        parent = path.dirname(target)
        if parent == target:
            return None
        target = parent
    return path.join(target, ZK_PROPERTIES)

def resolve_zk_config(target_path, timeout = 30):
    zk_config_path = find_zk_config(target_path)
    if zk_config_path is None:
        sys.exit("invalid path! can't find " + ZK_PROPERTIES)

    zk_config = {}
    for line in open(zk_config_path):
        arr = line.strip().split('=')
        if len(arr) != 2 or arr[0].startswith('#'):
            continue
        zk_config[arr[0]] = arr[1]

    if 'connectionString' not in zk_config:
        sys.exit('config[connectionString] missing!')
    if 'zkBasePath' not in zk_config:
        sys.exit('config[zkBasePath] missing!')

    zk_config['timeout'] = timeout
    zk_config['my_path'] = os.path.abspath(os.path.join(zk_config_path, os.path.pardir, os.path.pardir))
    return type('ZkConfig', (), zk_config)

def push_config_dir(local_path, zk_path, zookeeper):

    # 如果本地目标不存在，在ZooKeeper上删除相应的文件/文件夹
    if not path.exists(local_path):
        if zookeeper.exists(zk_path):
            zookeeper.delete(zk_path, True)
    else:
        local_targets = set(os.listdir(local_path))
        if not zookeeper.exists(zk_path):
            zk_targets = set()
        else:
            zk_targets = set(zookeeper.get_children(zk_path))

        targets_to_delete = zk_targets.difference(local_targets)
        for del_target in targets_to_delete:
            zookeeper.delete(zk_path + '/' + del_target, True)

        targets_to_create = local_targets.difference(zk_targets)
        for create_target in targets_to_create:
            zk_target_path = zk_path + '/' + create_target
            local_target_path = local_path + '/' + create_target
            if path.isdir(local_target_path):
                push_config_dir(local_target_path, zk_target_path, zookeeper)
            else:
                zookeeper.create_with_file(local_target_path, zk_target_path)

        targets_to_modify = local_targets.intersection(zk_targets)
        for modify_target in targets_to_modify:
            zk_target_path = zk_path + '/' + modify_target
            local_target_path = local_path + '/' + modify_target
            if path.isdir(local_target_path):
                push_config_dir(local_target_path, zk_target_path, zookeeper)
            else:
                zookeeper.modify_with_file(local_target_path, zk_target_path)


if __name__ == '__main__':
    target_dict = {}
    #pdb.set_trace()
    for target in sys.argv[1:]:
        appended = False
        push_target_path = path.realpath(path.abspath(target))
        for (zk_ppt_path, push_targets) in target_dict.items():
            if push_target_path.startswith(zk_ppt_path[0:len(zk_ppt_path)-len(ZK_PROPERTIES)]):
                push_targets.append(push_target_path)
                appended = True
                break
        if not appended:
            zk_config_path = find_zk_config(push_target_path)
            if zk_config_path is None:
                print 'illegal push target:', push_target_path
            else:
                target_dict[zk_config_path] = [ push_target_path ]
    for (zk_config_path, push_targets) in target_dict.items():
        zk_config = resolve_zk_config(zk_config_path)
        zookeeper = zk_client.zkClient(zk_config.connectionString, zk_config.timeout)
        try:
            for local_target_path in push_targets:
                zk_path_prefix = zk_config.zkBasePath + local_target_path[len(path.dirname(zk_config.my_path)):]
                # 如果本地目标不存在，在ZooKeeper上删除相应的文件/文件夹
                if not path.exists(local_target_path):
                    if zookeeper.exists(zk_path_prefix):
                        zookeeper.delete(zk_path_prefix, True)
                elif path.isfile(local_target_path):
                    if zookeeper.exists(zk_path_prefix):
                        zookeeper.modify_with_file(local_target_path, zk_path_prefix)
                    else:
                        zookeeper.create_with_file(local_target_path, zk_path_prefix)
                else:
                    push_config_dir(local_target_path, zk_path_prefix, zookeeper)
        except:
            print traceback.format_exc()
        finally:
            zookeeper.close()


