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
        self.switchPin(pin.boardId, pin.isSignalInverted, state)
        return self.updatePinState(pin)

    def switchPinByGpioId(self, gpioId, state):
        pin = self.getPinByGpioId(gpioId); # Make sure the pin is configured and get its board id
        self.switchPin(pin.boardId, pin.isSignalInverted, state)
        return self.updatePinState(pin)

    def switchPin(self, boardId, isSignalInverted, state):
        if state == 1:
            self.setPinOn(boardId, isSignalInverted)
        else:
            self.setPinOff(boardId, isSignalInverted)

    def togglePinByBoardId(self, boardId):
        pin = self.getPinByBoardId(boardId) # Make sure the pin is configured
        self.togglePin(pin.boardId, pin.isSignalInverted)
        return self.updatePinState(pin)

    def togglePinByGpioId(self, gpioId):
        pin = self.getPinByGpioId(gpioId) # Make sure the pin is configured and get the board id
        self.togglePin(pin.boardId, pin.isSignalInverted)
        return self.updatePinState(pin)

    def togglePin(self, boardId, isSignalInverted):
        if self.getPinState(boardId, isSignalInverted) == 1:
            self.switchPin(boardId, isSignalInverted, 0)
        else:
            self.switchPin(boardId, isSignalInverted, 1)

    def clickPinByBoardId(self, boardId, pressTimeMS):
        pin = self.getPinByBoardId(boardId) # Make sure the pin is configured
        self.clickPin(pin.boardId, pin.isSignalInverted, pressTimeMS)
        return self.updatePinState(pin)

    def clickPinByGpioId(self, gpioId, pressTimeMS):
        pin = self.getPinByGpioId(gpioId) # Make sure the pin is configured and get the board id
        self.clickPin(pin.boardId, pin.isSignalInverted, pressTimeMS)
        return self.updatePinState(pin)

    def clickPin(self, boardId, isSignalInverted, pressTimeMS):
        if pressTimeMS is None:
            pressTimeMS = self.config.servicePressTimeMS
        pressTimeSS = float(pressTimeMS) / 1000.0
        self.setPinOn(boardId, isSignalInverted)
        time.sleep(pressTimeSS)
        self.setPinOff(boardId, isSignalInverted)

    def getPinState(self, boardId, isSignalInverted):
        try:
            state = GPIO.input(boardId)
            if state: # might be null
                if isSignalInverted == 1:
                    return 0
                else:
                    return 1
            else:
                if isSignalInverted == 1:
                    return 1
                else:
                    return 0
        except: # pin might not be set up
            return 0

    def updatePinState(self, pin):
        pin.currentStatus = self.getPinState(pin.boardId, pin.isSignalInverted)
        return pin

    def getPinByBoardId(self, boardId):
        for pin in self.config.availablePins:
            if pin.boardId == boardId:
                pin.currentStatus = self.getPinState(pin.boardId, pin.isSignalInverted)
                return pin
        raise Exception("Pin [" + str(boardId) + "] is not available for use by this application")

    def getPinByGpioId(self, gpioId):
        for pin in self.config.availablePins:
            if pin.gpioId == gpioId:
                pin.currentStatus = self.getPinState(pin.boardId, pin.isSignalInverted)
                return pin
        raise Exception("GPIO Pin [" + str(gpioId) + "] is not available for use by this application")

    def setPinOn(self, boardId, isSignalInverted):
        if isSignalInverted == 1:
            self.turnPinOff(boardId)
        else:
            self.turnPinOn(boardId)

    def setPinOff(self, boardId, isSignalInverted):
        if isSignalInverted == 1:
            self.turnPinOn(boardId)
        else:
            self.turnPinOff(boardId)

    def initGPIO(self):
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        self.initPins()

    def initPins(self):
        for pin in self.config.availablePins:
            self.setPinOff(pin.boardId, pin.isSignalInverted)
            pin.currentStatus = 0

    def turnPinOff(self, boardId):
        GPIO.setup(boardId, GPIO.IN) # some realys just won't turn off unless turning off power to the pin

    def turnPinOn(self, boardId):
        GPIO.setup(boardId, GPIO.OUT) # In case power was turned off, turn it back on
        GPIO.output(boardId, GPIO.HIGH)

    def info(self):
        # Config might end up having other attributes that aren't supposed to be sent as status
        # So it's safer to create a new object instead of returning the config
        return {'instanceName': self.config.instanceName, 'availablePins': self.config.availablePins}