import { Icon } from './Icon';
import { PiInstancePin } from './PiInstancePin';
import { PinGroup } from './PinGroup';
import { UiButtonType } from './UiButtonType';

export class UiButton {
    constructor(
        public id : number,
        public title : string,
        public iconId : number,
        public type : UiButtonType,
        public linkedToPin : PiInstancePin,
        public linkedToPinGroup : PinGroup,
        public state : number,
        public order : number
    ){}
}