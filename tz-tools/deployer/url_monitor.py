'''
Created on Oct 24, 2014

@author: chengwen.li
'''

import httplib, urllib, threading, time, socket, errno

class UrlMonitor(threading.Thread):
    def __init__(self, url, duration, logger, finish_callback):
        threading.Thread.__init__(self)
        self.url = url
        self.duration = int(duration)
        self.logger = logger
        self.finish_callback = finish_callback
        self.terminated = False

    def run(self):
        protocol, temp = urllib.splittype(self.url)  # @UnusedVariable
        host, uri =  urllib.splithost(temp)
        host, port = urllib.splitport(host)
        if port is None:
            port = 80
        response = None
        self.logger.info('monitor url:' + self.url)
        self.start_time = time.time()
        while True:
            if self.should_stop():
                break
            try:
                httpclient = httplib.HTTPConnection(host, port, timeout=3)
                #httpclient = httplib.HTTPConnection(host, port, duration=self.duration)
                httpclient.request('GET', uri)
                response = httpclient.getresponse()
                break
            except socket.timeout:
                if self.should_stop():
                    self.logger.error('request ' + self.url + ' failed!')
                    break
#                 self.logger.warn('request[' + self.url + '] timeout, wait 2 seconds.')
                time.sleep(2)
            except socket.error as err:
                if self.should_stop():
                    self.logger.error('cannot connect to ' + self.url)
                    break
                if err.errno != errno.ECONNREFUSED:
                    self.logger.error(err)
                    break
                self.logger.warn('connection[%s:%s] refused, wait 1 second.' % (host, port))
                time.sleep(1)

        if response is None:
            self.logger.error('monitor url failed!')
        else:
            if response.status >= 200 and response.status < 400:
                self.logger.info('start successfully.')
            else:
                self.logger.error('start failed!')
        self.finish_callback()

    def stop(self):
        self.terminated = True

    def should_stop(self):
        return self.terminated or (time.time() - self.start_time >= self.duration)
