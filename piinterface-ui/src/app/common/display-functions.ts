import { IpAddressRange } from '../model/IpAddressRange';
import { PiInstance } from '../model/PiInstance';
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

// Pin operating modes display functions
export function pinOperatingModeDisplayFunction(item : PinOperatingMode) { return item ? item.name : "not set"; }

