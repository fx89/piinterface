#!/usr/bin/python

CONST_PIN_TYPE_ID_PHYSICAL = 0
CONST_PIN_TYPE_ID_CONSOLE_COMMAND = 1

# Maps the pin's board id to its GPIO id
# Used as a DTO - a list of available pins will be provided by the service as stated in the config file
class Pin:
    def __init__(self, boardId, gpioId, isSignalInverted, currentStatus, name, pinTypeId, turnOnCommand, turnOffCommand, getStateCommand):
        self.boardId = boardId
        self.gpioId = gpioId
        self.currentStatus = currentStatus
        self.isSignalInverted = isSignalInverted
        self.name = name
        self.pinTypeId = pinTypeId
        self.turnOnCommand = turnOnCommand
        self.turnOffCommand = turnOffCommand
        self.getStateCommand = getStateCommand
