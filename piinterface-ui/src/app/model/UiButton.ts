import { PiInstancePin } from './PiInstancePin';
import { PinGroup } from './PinGroup';
import { UiButtonsPanel } from './UiButtonsPanel';
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
        public order : number,
        public targetPinState : number,
        public buttonsPanel : UiButtonsPanel
    ){}
}