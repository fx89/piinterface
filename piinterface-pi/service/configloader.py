#!/usr/bin/python

import configparser
import json

import sys
sys.path.insert(1, '../')

import model.config as CONF
import model.pin as PIN

def loadConfig(filePathName):
    config = configparser.ConfigParser()
    config.read(filePathName)
    return CONF.Config(config['Instance']['name'], int(config['Service']['pressTimeMS']), loadPins(config))

def loadPins(config):
    ret = []
    for i in range(0,41):
        try:
            pinData = json.loads(config['Pins']["Pin{0}".format(i)])
            ret.append(PIN.Pin(int(pinData['boardId']), int(pinData['gpioId']), int(pinData['isSignalInverted']), 0, pinData['name']))
        except:
            n = 1
    return ret
