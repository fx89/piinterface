import { PiInstancePin } from './PiInstancePin';
import { PinCalendarAction } from './PinCalendarAction';

export class PinCalendarEntry {
    constructor(
        public id : number,
        public title : string,
        public pin : PiInstancePin,
        public year : number,
        public month : number,
        public day : number,
        public hour : number,
        public minute : number,
        public action : PinCalendarAction
    ){}

    public static newEntry() : PinCalendarEntry {
        return new PinCalendarEntry(
            null,
            "New entry",
            null,
            -1,
            -1,
            -1,
            -1,
             0,
            null
        );
    }
}