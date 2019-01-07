import { User, UserFactory } from "../user/user.model";
import { Space, SpaceFactory } from "../space/space.model";

export interface SpaceSlot {
    id: number;
    user: User;
    familyLink: string;
    validationState: string;
    tokenValidation: string;
    urgenceContact: boolean;
    roles: string[];
    space: Space;
}

export class SpaceSlotFactory {
    public static createEmptySpaceSlot(): SpaceSlot {
        return {
            id: 0,
            familyLink: null,
            tokenValidation: null,
            urgenceContact: false,
            user: UserFactory.createEmptyUser(),
            validationState: 'WAITING',
            roles: [],
            space: SpaceFactory.createEmptySpace()
        };
    }
}