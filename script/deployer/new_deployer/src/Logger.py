
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''

from Colorer import ColorizingStreamHandler
import logging

colorLogger = logging.getLogger("color")
colorLogger.setLevel(logging.INFO)
colorLogger.addHandler(ColorizingStreamHandler())


plainLogger = logging.getLogger("plain")
plainLogger.setLevel(logging.INFO)
plainLogger.addHandler(logging.StreamHandler())


