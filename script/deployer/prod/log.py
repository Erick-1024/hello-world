#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu, Xinxin.xu
# mail: jitao.chu@travelzen.com, xinxin.xu@travelzen.com

import re
import os
import sys
import datetime
import ConfigParser

class WriteLog(object):
    def __init__(self, log_options, log_debug_file='', log_error_file='', log_label='localhost'):
        init_log = ConfigParser.ConfigParser()
        init_log.read("init.cfg")
        if log_debug_file == '':
            log_debug_file = init_log.get('other', 'debug_log')
        if log_error_file == '':
            log_error_file = init_log.get('other', 'error_log')
        try:
            if not os.path.isdir(os.path.dirname(log_debug_file)):
                os.makedirs(os.path.dirname(log_debug_file))
        except OSError as e:
            if re.match('.*Permission denied.*', str(e)):
                if not os.path.isdir('%s%s'% (os.environ['HOME'], os.path.dirname(log_debug_file))):
                    os.makedirs('%s%s'% (os.environ['HOME'], os.path.dirname(log_debug_file)))
                log_debug_file = '%s%s'% (os.environ['HOME'], log_debug_file)
        try:
            if not os.path.isdir(os.path.dirname(log_error_file)):
                os.makedirs(os.path.dirname(log_error_file))
        except OSError as e:
            if re.match('.*Permission denied.*', str(e)):
                if not os.path.isdir('%s%s'% (os.environ['HOME'], os.path.dirname(log_error_file))):
                    os.makedirs('%s%s'% (os.environ['HOME'], os.path.dirname(log_error_file)))
                log_error_file = '%s%s'% (os.environ['HOME'], log_error_file)
        self.debug_log = open(log_debug_file, 'a', 1)
        self.error_log = open(log_error_file, 'a', 1)
        self.options = log_options
        self.label = log_label
        
    def ErrorLog(self, log):
        print('\033[1;31m[%s ERROR]: %s\t(%s)\033[0m\n' % (datetime.datetime.now().strftime("%H:%M:%S"), log, self.label))
        sys.stdout.flush()
        self.error_log.write('[%s ERROR]: %s\t(%s)\n' % (datetime.datetime.now().strftime("%H:%M:%S"), log, self.label))
        
    def WarnLog(self, log):
        print('[%s WARN]: %s\t(%s)\n' % (datetime.datetime.now().strftime("%H:%M:%S"), log, self.label))
        sys.stdout.flush()
        self.debug_log.write('[%s WARN]: %s\t(%s)\n' % (datetime.datetime.now().strftime("%H:%M:%S"), log, self.label))
            
    def DebugLog(self, log):
        print('[%s DEBUG]: %s\t(%s)\n' % (datetime.datetime.now().strftime("%H:%M:%S"), log, self.label))
        sys.stdout.flush()
        self.debug_log.write('[%s DEBUG]: %s\t(%s)\n' % (datetime.datetime.now().strftime("%H:%M:%S"), log, self.label))
    
    def TimeMarker(self):
        print('\n\033[1;32m[ %s ]\tTime Marker: %s\tStatus: begin\033[0m\n' % (datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), self.label))
        self.debug_log.write('\n[ %s ]\tTime Marker: %s\tStatus: begin\n' % (datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), self.label))
        self.error_log.write('\n[ %s ]\tTime Marker: %s\tStatus: begin\n' % (datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), self.label))
    
    def CloseLog(self):
        print('\n\033[1;32m[ %s ]\tTime Marker: %s\tStatus: finish\033[0m\n' % (datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), self.label))
        self.debug_log.write('\n[ %s ]\tTime Marker: %s\tStatus: finish\n' % (datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), self.label))
        self.error_log.write('\n[ %s ]\tTime Marker: %s\tStatus: finish\n' % (datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), self.label))
        self.debug_log.close()
        self.error_log.close()
