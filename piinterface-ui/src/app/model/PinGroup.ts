import { PinGroupType } from './PinGroupType';

export class PinGroup {
    constructor(
        public id : number,
        public name :string,
        public type : PinGroupType
    ){}
}