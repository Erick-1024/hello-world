
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''

def is_positive_int(str_or_num):
    ''' 判断传入的参数是否是正整数 '''
    
    if isinstance(str_or_num, ( int, long ) ) and str_or_num > 0:
        return True;

    if isinstance(str_or_num, basestring) and str_or_num.isdigit():
        return True
    
    return False
