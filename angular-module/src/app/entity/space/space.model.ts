import { SpaceSlot } from "../space-slot/space-slot.model";

export interface LightSpace {
    id: number;
    name: string;
}

export interface Space extends LightSpace {
    spaceSlots: SpaceSlot[];
}

export class SpaceFactory {
    public static createEmptySpace(): Space {
        return {
            id: 0,
            name: null,
            spaceSlots: []
        };
    }
}
