import { IpAddressRange } from '../model/IpAddressRange';
import { PiInstance } from '../model/PiInstance';
import { PiInstancePin } from '../model/PiInstancePin';
import { PinGroup } from '../model/PinGroup';
import { PinGroupPin } from '../model/PinGroupPin';
import { PinOperatingMode } from '../model/PinOperatingMode';
import { getSkinName } from "../utils/skin-utils";

// PI instance display functions
export function piInstanceTitleFunction(item : PiInstance) { return item ? item.name : ""; }
export function piInstanceDescriptionFunction(item : PiInstance) { return item ? ("[" + item.id + "] " + item.lastRegisteredAddress + (item.isOffline ? " (OFFLINE)" : "")) : ""; }
export function piInstanceIconFunction() { return "assets/skins/" + getSkinName() + "/icons/main-menu/pi-instances.png"; }

// IP address rannges display functions
export function ipRangeTitleFunction(item : IpAddressRange) { return item ? item.name : ""; }
export function ipRangeDescriptionFunction(item : IpAddressRange) { return item ? ("[" + item.id + "] " + item.prefix + "(" + item.rangeStart + "/" + item.rangeEnd + ")") : ""; }
export function ipRangeIconFunction() { return "assets/skins/" + getSkinName() + "/icons/list-element-squashed.png"; }

// Pins display functions
export function pinTitleDisplayFunction(item : PiInstancePin) { return item ? item.name : ""; }
export function pinDescriptionDisplayFunction(item : PiInstancePin) { return item ? ("[" + item.id + "] " + item.name + " (" + item.boardId + "/" + item.gpioId + ")" + (item.isAvailable ? "" : " - Not available")) : ""; }
export function pinIconFunction() { return "assets/skins/" + getSkinName() + "/icons/main-menu/pins.png"; }

// Pin operating modes display functions
export function pinOperatingModeDisplayFunction(item : PinOperatingMode) { return item ? item.name : "not set"; }

// Pin groups display functions
export function pinGroupTitleFunction(item:PinGroup) { return item ? item.name : ""; }
export function pinGroupDescriptionFunction(item:PinGroup) { return item ? ("[" + item.id + "] " + (item.type ? item.type.name : "type not selected")) : ""; }
export function pinGroupIconFunction() { return "assets/skins/" + getSkinName() + "/icons/main-menu/pin-groups.png"; }

// Pin group pins display functions
export function pinGroupPinTitleFunction(item:PinGroupPin) { return item ? (item.pin.piInstance.name + " / " + item.pin.name) : ""; }
export function pinGroupPinDescriptionFunction(item:PinGroupPin) { return item ? ("[" + item.order.toString() + "] (" + item.pin.boardId + "/" + item.pin.gpioId + ")" + (item.pin.isAvailable ? "" : "not available")) : ""; }