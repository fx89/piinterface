import { PiInstancePin } from './PiInstancePin';
import { PinGroup } from './PinGroup';

export class PinGroupPin {
    constructor(
        public id : number,
        public pinGroup : PinGroup,
        public pin : PiInstancePin,
        public order : number
    ){}
}