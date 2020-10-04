
export function arrRemove(arr : any[], element : any, equalsOperator : Function = (a,b) => a == b) : any[] {
    if (arr) {
        let ret : any[] = [];
        for (let n = 0 ; n < arr.length ; n++) {
            let arrElement = arr[n];
            if (equalsOperator(arrElement, element) == false) {
                ret.push(arrElement);
            }
        }
        return ret;
    } else {
        return null;
    }
}