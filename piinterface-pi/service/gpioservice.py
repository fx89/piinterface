#!/usr/bin/python

import RPi.GPIO as GPIO
import time

class GPIOService:
    def __init__(self, config):
        self.config = config;
        self.initGPIO()

    def getFreePins(self):
        return self.config.freePins;

    def switchPinByBoardId(self, boardId, state):
        pin = self.getPinByBoardId(boardId); # Make sure the pin is configured
        self.switchPin(pin.boardId, state)
        return self.updatePinState(pin)

    def switchPinByGpioId(self, gpioId, state):
        pin = self.getPinByGpioId(gpioId); # Make sure the pin is configured and get its board id
        self.switchPin(pin.boardId, state)
        return self.updatePinState(pin)

    def switchPin(self, boardId, state):
        if state == 1:
            self.setPinOn(boardId)
        else:
            self.setPinOff(boardId)

    def togglePinByBoardId(self, boardId):
        pin = self.getPinByBoardId(boardId) # Make sure the pin is configured
        self.togglePin(pin.boardId)
        return self.updatePinState(pin)

    def togglePinByGpioId(self, gpioId):
        pin = self.getPinByGpioId(gpioId) # Make sure the pin is configured and get the board id
        self.togglePin(pin.boardId)
        return self.updatePinState(pin)

    def togglePin(self, boardId):
        if self.getPinState(boardId) == 1:
            self.switchPin(boardId, 0)
        else:
            self.switchPin(boardId, 1)

    def clickPinByBoardId(self, boardId, pressTimeMS):
        pin = self.getPinByBoardId(boardId) # Make sure the pin is configured
        self.clickPin(pin.boardId, pressTimeMS)
        return self.updatePinState(pin)

    def clickPinByGpioId(self, gpioId, pressTimeMS):
        pin = self.getPinByGpioId(gpioId) # Make sure the pin is configured and get the board id
        self.clickPin(pin.boardId, pressTimeMS)
        return self.updatePinState(pin)

    def clickPin(self, boardId, pressTimeMS):
        if pressTimeMS is None:
            pressTimeMS = self.config.servicePressTimeMS
        pressTimeSS = float(pressTimeMS) / 1000.0
        self.setPinOn(boardId)
        time.sleep(pressTimeSS)
        self.setPinOff(boardId)

    def getPinState(self, boardId):
        try:
            state = GPIO.input(boardId)
            if state: # might be null
                return 1
            else:
                return 0
        except: # pin might not be set up
            return 0

    def updatePinState(self, pin):
        pin.currentStatus = self.getPinState(pin.boardId)
        return pin

    def getPinByBoardId(self, boardId):
        for pin in self.config.availablePins:
            if pin.boardId == boardId:
                pin.currentStatus = self.getPinState(pin.boardId)
                return pin
        raise Exception("Pin [" + str(boardId) + "] is not available for use by this application")

    def getPinByGpioId(self, gpioId):
        for pin in self.config.availablePins:
            if pin.gpioId == gpioId:
                pin.currentStatus = self.getPinState(pin.boardId)
                return pin
        raise Exception("GPIO Pin [" + str(gpioId) + "] is not available for use by this application")

    def setPinOn(self, boardId):
        GPIO.output(boardId, GPIO.HIGH)

    def setPinOff(self, boardId):
        GPIO.output(boardId, GPIO.LOW)

    def initGPIO(self):
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        self.initPinsForOutput()

    def initPinsForOutput(self):
        for pin in self.config.availablePins:
            GPIO.setup(pin.boardId, GPIO.OUT)
            self.setPinOff(pin.boardId)
            pin.currentStatus = 0

    def info(self):
        # Config might end up having other attributes that aren't supposed to be sent as status
        # So it's safer to create a new object instead of returning the config
        return {'instanceName': self.config.instanceName, 'availablePins': self.config.availablePins}