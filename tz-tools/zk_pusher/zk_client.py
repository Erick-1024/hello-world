# -*- coding: utf-8 -*-
# author: Xinxin.xu
# mail: xinxin.xu@travelzen.com
# modifier: chengwen.li
import sys, os.path, re
import kazoo.handlers, kazoo.client
import colorful_printer as printer

class zkClient:

    def __init__(self, hosts, timeout = 30.0):
        printer.green('==> connecting ' + hosts)
        try:
            self.zkClient = kazoo.client.KazooClient(hosts, timeout)
            self.zkClient.start()
        except kazoo.handlers.threading.TimeoutError as e:
            self.zkClient = ''
            print 'zookeeper_%s: %s' % (hosts, e)
            sys.exit(e)

    def create(self, path, value, create_parents=True):
        self.zkClient.create(path, value, makepath=create_parents)

    def create_with_file(self, local_file, zk_path):
        zk_path = re.sub(r'\/{2,}', '/', zk_path)
        printer.green('[create] => ' + zk_path)
        value = open(local_file).read()
        self.create(zk_path, value)

    def delete(self, zk_path, delete_children=False):
        zk_path = re.sub(r'\/{2,}', '/', zk_path)
        printer.red('[delete] => ' + zk_path)
        try:
            self.zkClient.delete(zk_path, recursive=delete_children)
        except kazoo.exceptions.BadArgumentsError:
            pass

    def set(self, path, value):
        self.zkClient.set(path, value)

    def modify_with_file(self, local_file, zk_path):
        zk_path = re.sub(r'\/{2,}', '/', zk_path)
        printer.magenta('[modify] => ' + zk_path)
        value = open(local_file).read()
        self.set(zk_path, value)

    def close(self):
        self.zkClient.stop()
        self.zkClient.close()

    def get(self, path):
        return self.zkClient.get(path)

    def get_children(self, path):
        return self.zkClient.get_children(path)

    def exists(self, path):
        node_stat = self.zkClient.exists(path)
        return node_stat != None

