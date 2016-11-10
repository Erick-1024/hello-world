#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import subprocess
import tzOps
import log
import os

class syncing(object):
    def __init__(self, jiraid, ops_tag, update_tag, tasks, options):
        self.git = '%s %s' % (tasks['git'], update_tag)
        self.rsync =  tasks['rsync']
        self.jiraid = jiraid
        self.ops_tag = ops_tag
        self.update_tag = update_tag
        self.options = options
        self.log_proto = log.WriteLog(log_options=self.options, log_label='proto-b2b')
        self.log_proto.TimeMarker()

    def localCmd(self, cmd):
        self.log_proto.DebugLog(cmd)
        if not '--check' in self.options:
            p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            retval = p.wait()
            if retval != 0:
                self.log_proto.ErrorLog(p.stderr.read())
                return False
            self.log_proto.DebugLog(p.stdout.read())
        return True
    
    def localCmd_tmp(self, cmd):
        self.log_proto.DebugLog(cmd)
        if not '--check' in self.options:
            os.system(cmd)
        return True
 
    def main(self):
        if not self.localCmd(self.git):
            self.log_proto.ErrorLog(self.git)
            self.log_proto.CloseLog()
            return False
        #if not self.localCmd(self.rsync):
        if not self.localCmd_tmp(self.rsync):
            self.log_proto.ErrorLog(self.rsync)
            self.log_proto.CloseLog()
            return False
        retval = tzOps.ops().log(self.ops_tag, self.rsync.split()[-1].split(':')[0], 'proto-b2b', self.jiraid)
        if retval == '1':
            self.log_proto.DebugLog('tzOps: log to ops %s %s %s successfully' % (self.ops_tag, self.rsync.split()[-1].split(':')[0], 'proto-b2b'))
        else:
            self.log_proto.WarnLog('tzOps: log to ops %s %s %s failed' % (self.ops_tag, self.rsync.split()[-1].split(':')[0], 'proto-b2b'))
            self.log_proto.WarnLog('tzOps: retval -> %s' % retval)
        self.log_proto.CloseLog()
        return True
