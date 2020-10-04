export class IpAddressRange {
    constructor(
        public id : number,
        public name : string,
        public prefix : string,
        public rangeStart : string,
        public rangeEnd : string
    ){}
}