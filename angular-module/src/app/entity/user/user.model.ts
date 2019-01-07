import { SpaceSlot } from "../space-slot/space-slot.model";

export interface User {
    username: string | null;
    firstname: string | null;
    lastname: string | null;
    mobile: string | null;
    phone: string | null;
    spaceSlots: SpaceSlot[] | [];
}

export class UserFactory {
    public static createEmptyUser(): User {
        return {
            username: null,
            firstname: null,
            lastname: null,
            spaceSlots: [],
            mobile: null,
            phone: null
        };
    }
}