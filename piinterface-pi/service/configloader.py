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
    physicalPins = loadPhysicalPins(config)
    consoleCommandPins = loadConsoleCommandPins(config)
    ret = []
    for e in physicalPins:
        ret.append(e)
    for e in consoleCommandPins:
        ret.append(e)
    return ret;

def loadPhysicalPins(config):
    ret = []
    for i in range(0,41):
        try:
            pinData = json.loads(config['Pins']["Pin{0}".format(i)])
            ret.append(PIN.Pin(int(pinData['boardId']), int(pinData['gpioId']), int(pinData['isSignalInverted']), 0, pinData['name'], PIN.CONST_PIN_TYPE_ID_PHYSICAL, '', '', ''))
        except Exception as e:
            if len(str(e)) > 8:
                print(e)
    return ret

def loadConsoleCommandPins(config):
    ret = []
    for i in range(100,199):
        try:
            pinData = json.loads(config['Console Commands']["Pin{0}".format(i)])
            ret.append(PIN.Pin(i, i, 0, 0, pinData['name'], PIN.CONST_PIN_TYPE_ID_CONSOLE_COMMAND, pinData['turnOnCommand'], pinData['turnOffCommand'], pinData['getStateCommand']))
        except Exception as e:
            if len(str(e)) > 8:
                print(e)
    return ret


