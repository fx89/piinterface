import { PinOperatingMode } from './PinOperatingMode';

export class PiInstancePin {
    constructor(
        public id : number,
        public name : string,
        public boardId : number,
        public gpioId : number,
        public delayMs : number,
        public isAvailable : boolean,
        public operatingMode : PinOperatingMode,
        public delayMS : number
    ){}
}