#!/usr/bin/python

# Maps the pin's board id to its GPIO id
# Used as a DTO - a list of available pins will be provided by the service as stated in the config file
class Pin:
    def __init__(self, boardId, gpioId, currentStatus):
        self.boardId = boardId
        self.gpioId = gpioId
        self.currentStatus = currentStatus