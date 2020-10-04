export class BackendNotification {
    constructor(
        public type : number,
        public date : Date,
        public title : string,
        public message : string
    ){}
}