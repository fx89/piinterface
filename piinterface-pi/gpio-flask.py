#!/usr/bin/python

# use pip install flask
# then:
#   export FLASK_APP=gpio-flask.py
#   flask run -p 4001
#
# Available calls ===================================================================

# info
#   returns the instance information

# switchPinByBoardId
#   sets the given state to the pin identified with the given board id and returns
#   the pin data so that the current status may be accessed
#
#   params:
#       boardId = the physical pin id
#       state   = the thate (1 or 0) to which to switch the pin

# switchPinByGpioId
#   sets the given state to the pin identified with the given GPIO id and returns
#   the pin data so that the current status may be accessed
#
#   params:
#       gpioId = the GPIO pin id or a custom id given in the config file
#       state  = the thate (1 or 0) to which to switch the pin

# togglePinByBoardId
#   toggles the state pf the pin identified with the given board id and returns
#   the pin data so that the current status may be accessed
#
#   params:
#       boardId = the physical pin id

# togglePinByGpioId
#   toggles the state pf the pin identified with the given GPIO id and returns
#   the pin data so that the current status may be accessed
#
#   params:
#       gpioId = the GPIO pin id or a custom id given in the config file

# clickPinByBoardId
#   toggles the pin identified by the given board id off and then on, after a
#   given period of time, thus emulating the click of a pushbutton
#
#   params:
#       boardId     = the physical pin id
#       pressTimeMS = <optional> how much time (milliseconds) to keep the pin on (default is in the config file)

# clickPinByGpioId
#   toggles the pin identified by the given GPIO id off and then on, after a
#   given period of time, thus emulating the click of a pushbutton
#
#   params:
#       gpioId      = the physical pin id
#       pressTimeMS = <optional> how much time (milliseconds) to keep the pin on (default is in the config file)

# getPinByBoardId
#   returns the state of the pin with the given board id
#
#   params:
#       boardId = the physical pin id

# getPinByGpioId
#   returns the state of the pin with the given GPIO id
#
#   params:
#      gpioId = the GPIO pin id or a custom id given in the config file

# ===================================================================================

# FLASK dependencies
from flask import Flask, request
from flask import jsonify

import json

import service.configloader as CFG
import service.gpioservice as GPS

# Init the app
app = Flask(__name__)

# Init the service
config = CFG.loadConfig("config.ini")
gpioService = GPS.GPIOService(config)

# Utilities =========================================================================

def toJSON(obj):
    return json.dumps(obj, default=lambda o: o.__dict__, sort_keys=True, indent=4)

# Define routes to the service functionality ========================================

@app.route('/info', methods=['GET'])
def info():
    return toJSON(gpioService.info())

@app.route('/switchPinByBoardId', methods=['GET'])
def switchPinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        state = int(request.args.get('state'))
        return toJSON(gpioService.switchPinByBoardId(boardId, state))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/switchPinByGpioId', methods=['GET'])
def switchPinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        state = int(request.args.get('state'))
        return toJSON(gpioService.switchPinByGpioId(gpioId, state))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/togglePinByBoardId', methods=['GET'])
def togglePinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        return toJSON(gpioService.togglePinByBoardId(boardId))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/togglePinByGpioId', methods=['GET'])
def togglePinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        return toJSON(gpioService.togglePinByGpioId(gpioId))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/clickPinByBoardId', methods=['GET'])
def clickPinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        try:
            pressTimeMS = int(request.args.get('pressTimeMS'))
        except:
            pressTimeMS = None
        return toJSON(gpioService.clickPinByBoardId(boardId, pressTimeMS))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/clickPinByGpioId', methods=['GET'])
def clickPinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        try:
            pressTimeMS = int(request.args.get('pressTimeMS'))
        except:
            pressTimeMS = None
        return toJSON(gpioService.clickPinByGpioId(gpioId, pressTimeMS))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/getPinByBoardId', methods=['GET'])
def getPinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        return toJSON(gpioService.getPinByBoardId(boardId))
    except Exception as e:
        return toJSON({"message": str(e)}), 500

@app.route('/getPinByGpioId', methods=['GET'])
def getPinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        return toJSON(gpioService.getPinByGpioId(gpioId))
    except Exception as e:
        return toJSON({"message": str(e)}), 500


