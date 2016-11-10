#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author: Jitao.chu
# mail: jitao.chu@travelzen.com

import urllib2

class ops(object):

    def __init__(self):
        self.nginxURL = 'http://ops.travelzen.cn/ops/loadbalance.php?act=ifupdate'
        self.nginxData = 'lic=deploy_jitao&svip=192.168.240.249'

    def __HttpRequest(self, fullurl, requestData):
        self.req = urllib2.Request(fullurl, requestData)
        self.responseData = urllib2.urlopen(self.req)
        return self.responseData.read()

    def online(self, pool, node, port):
        self.data = '%s&pool=%s&node=%s&port=%s&cstatus=1&reason=deploy' % (self.nginxData, pool, node, port)
        return self.__HttpRequest(self.nginxURL, self.data)

    def offline(self, pool, node, port):
        self.data = '%s&pool=%s&node=%s&port=%s&cstatus=2&reason=deploy' % (self.nginxData, pool, node, port)
        return self.__HttpRequest(self.nginxURL, self.data)

    def log(self, tag, server, project, jiraid):
        self.logURL = 'http://ops.travelzen.cn/pub/deploy/rlslog.php'
        self.logData = 'lic=nb2brls&act=log&cid=%s&cms=%s&app=%s&jiraid=%s' % (tag, server, project, jiraid)
        return self.__HttpRequest(self.logURL, self.logData)


if __name__ == '__main__':
    pass
    #print ops().offline('member_tdxinfo_com', '192.168.241.66', '8180')
    #print ops().log('T_1.0.8', '192.168.241.66', 'tops-front-purchaser')
