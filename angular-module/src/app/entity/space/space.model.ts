import { User } from "../user/user.model";

export interface LightSpace {
    id: number;
    name: string;
}

export interface Space extends LightSpace {
    spaceSlots: SpaceSlot[];
}

export interface SpaceSlot {
    id: number;
    user: User;
    familyLink: string;
    validationState: string;
    tokenValidation: string;
    urgenceContact: boolean;
    roles: string[];
}