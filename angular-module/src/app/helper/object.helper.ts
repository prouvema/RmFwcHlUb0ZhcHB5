export class ObjectHelper {

    public static newObject(obj1: any, obj2?: any): any {
        if (obj2) {
            return Object.assign({}, obj1, obj2);
        }
        return Object.assign({}, obj1);
    }
}