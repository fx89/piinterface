#!/usr/bin/python

# use pip install flask
# then:
#   export FLASK_APP=gpio-flask.py
#   flask run -p 4001 --host='ip.of.the.pi' --- ONLY FOR TESTING !!!
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
from flask import Flask, request, Response

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
    try:
        return Response(toJSON(gpioService.info()), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/switchPinByBoardId', methods=['GET'])
def switchPinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        state = int(request.args.get('state'))
        return Response(toJSON(gpioService.switchPinByBoardId(boardId, state)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/switchPinByGpioId', methods=['GET'])
def switchPinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        state = int(request.args.get('state'))
        return Response(toJSON(gpioService.switchPinByGpioId(gpioId, state)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/togglePinByBoardId', methods=['GET'])
def togglePinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        return Response(toJSON(gpioService.togglePinByBoardId(boardId)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/togglePinByGpioId', methods=['GET'])
def togglePinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        return Response(toJSON(gpioService.togglePinByGpioId(gpioId)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/clickPinByBoardId', methods=['GET'])
def clickPinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        try:
            pressTimeMS = int(request.args.get('pressTimeMS'))
        except:
            pressTimeMS = None
        return Response(toJSON(gpioService.clickPinByBoardId(boardId, pressTimeMS)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/clickPinByGpioId', methods=['GET'])
def clickPinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        try:
            pressTimeMS = int(request.args.get('pressTimeMS'))
        except:
            pressTimeMS = None
        return Response(toJSON(gpioService.clickPinByGpioId(gpioId, pressTimeMS)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/getPinByBoardId', methods=['GET'])
def getPinByBoardId():
    try:
        boardId = int(request.args.get('boardId'))
        return Response(toJSON(gpioService.getPinByBoardId(boardId)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")

@app.route('/getPinByGpioId', methods=['GET'])
def getPinByGpioId():
    try:
        gpioId = int(request.args.get('gpioId'))
        return Response(toJSON(gpioService.getPinByGpioId(gpioId)), 200, mimetype="application/json")
    except Exception as e:
        return Response(toJSON({"message": str(e)}), 500, mimetype="application/json")


