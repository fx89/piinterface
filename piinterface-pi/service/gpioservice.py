#!/usr/bin/python

import RPi.GPIO as GPIO
import time
import os
import model.pin as PIN

class GPIOService:
    def __init__(self, config):
        self.config = config;
        self.initGPIO()

    def switchPinByBoardId(self, boardId, state):
        pin = self.getPinByBoardId(boardId); # Make sure the pin is configured
        self.switchPin(pin, state)
        return self.updatePinState(pin)

    def switchPinByGpioId(self, gpioId, state):
        pin = self.getPinByGpioId(gpioId); # Make sure the pin is configured and get its board id
        self.switchPin(pin, state)
        return self.updatePinState(pin)

    def switchPin(self, pin, state):
        if state == 1:
            self.setPinOn(pin)
        else:
            self.setPinOff(pin)

    def togglePinByBoardId(self, boardId):
        pin = self.getPinByBoardId(boardId) # Make sure the pin is configured
        self.togglePin(pin)
        return self.updatePinState(pin)

    def togglePinByGpioId(self, gpioId):
        pin = self.getPinByGpioId(gpioId) # Make sure the pin is configured and get the board id
        self.togglePin(pin)
        return self.updatePinState(pin)

    def togglePin(self, pin):
        if self.getPinState(pin) == 1:
            self.switchPin(pin, 0)
        else:
            self.switchPin(pin, 1)

    def clickPinByBoardId(self, boardId, pressTimeMS):
        pin = self.getPinByBoardId(boardId) # Make sure the pin is configured
        self.clickPin(pin, pressTimeMS)
        return self.updatePinState(pin)

    def clickPinByGpioId(self, gpioId, pressTimeMS):
        pin = self.getPinByGpioId(gpioId) # Make sure the pin is configured and get the board id
        self.clickPin(pin, pressTimeMS)
        return self.updatePinState(pin)

    def clickPin(self, pin, pressTimeMS):
        if pressTimeMS is None:
            pressTimeMS = self.config.servicePressTimeMS
        pressTimeSS = float(pressTimeMS) / 1000.0
        self.setPinOn(pin)
        time.sleep(pressTimeSS)
        self.setPinOff(pin)

    def getPinState(self, pin):
        if pin.pinTypeId == PIN.CONST_PIN_TYPE_ID_PHYSICAL:
            return self.getPhysicalPinState(pin)
        else:
            if pin.pinTypeId == PIN.CONST_PIN_TYPE_ID_CONSOLE_COMMAND:
                return self.getConsoleCommandPinState(pin)
            else:
                return 0

    def getPhysicalPinState(self, pin):
        try:
            state = 0
            if GPIO.gpio_function(pin.boardId) == GPIO.OUT:
                state = GPIO.input(pin.boardId)

            if state: # might be null
                if pin.isSignalInverted == 1:
                    return 0
                else:
                    return 1
            else:
                if pin.isSignalInverted == 1:
                    return 1
                else:
                    return 0
        except: # pin might not be set up
            return 0

    def getConsoleCommandPinState(self, pin):
        try:
            if os.system(pin.getStateCommand) > 0:
                return 1
            else:
                return 0
        except:
            return 0

    def updatePinState(self, pin):
        pin.currentStatus = self.getPinState(pin)
        return pin

    def getPinByBoardId(self, boardId):
        for pin in self.config.availablePins:
            if pin.boardId == boardId:
                pin.currentStatus = self.getPinState(pin)
                return pin
        raise Exception("Pin [" + str(boardId) + "] is not available for use by this application")

    def getPinByGpioId(self, gpioId):
        for pin in self.config.availablePins:
            if pin.gpioId == gpioId:
                pin.currentStatus = self.getPinState(pin)
                return pin
        raise Exception("GPIO Pin [" + str(gpioId) + "] is not available for use by this application")

    def setPinOn(self, pin):
        if pin.isSignalInverted == 1:
            self.turnPinOff(pin)
        else:
            self.turnPinOn(pin)

    def setPinOff(self, pin):
        if pin.isSignalInverted == 1:
            self.turnPinOn(pin)
        else:
            self.turnPinOff(pin)

    def initGPIO(self):
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        self.initPins()

    def initPins(self):
        for pin in self.config.availablePins:
            self.setPinOff(pin)
            pin.currentStatus = 0

    def turnPinOff(self, pin):
        if pin.pinTypeId == PIN.CONST_PIN_TYPE_ID_PHYSICAL:
            self.turnPhysicalPinOff(pin)
        else:
            if pin.pinTypeId == PIN.CONST_PIN_TYPE_ID_CONSOLE_COMMAND:
                self.turnConsoleCommandPinOff(pin)

    def turnPhysicalPinOff(self, pin):
        if GPIO.gpio_function(pin.boardId) == GPIO.OUT:
            GPIO.setup(pin.boardId, GPIO.OUT)
            GPIO.output(pin.boardId, GPIO.LOW)
        GPIO.setup(pin.boardId, GPIO.IN) # some realys just won't turn off unless turning off power to the pin

    def turnConsoleCommandPinOff(self, pin):
        os.system(pin.turnOffCommand)

    def turnPinOn(self, pin):
        if pin.pinTypeId == PIN.CONST_PIN_TYPE_ID_PHYSICAL:
            self.turnPhysicalPinOn(pin)
        else:
            if pin.pinTypeId == PIN.CONST_PIN_TYPE_ID_CONSOLE_COMMAND:
                self.turnConsoleCommandPinOn(pin)

    def turnPhysicalPinOn(self, pin):
        GPIO.setup(pin.boardId, GPIO.OUT) # In case power was turned off, turn it back on
        GPIO.output(pin.boardId, GPIO.HIGH)

    def turnConsoleCommandPinOn(self, pin):
        os.system(pin.turnOnCommand)

    def info(self):
        # Config might end up having other attributes that aren't supposed to be sent as status
        # So it's safer to create a new object instead of returning the config
        return {'instanceName': self.config.instanceName, 'availablePins': self.config.availablePins}