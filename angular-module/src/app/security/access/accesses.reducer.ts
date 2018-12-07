import { All, AccessesActionTypes } from "./accesses.action";

export type Action = All;

export interface State {
    accesses: string[] | [];
}

export const initialState: State = {
    accesses: [],
};

export function reducer(state = initialState, action: Action): State {
    switch (action.type) {
        case AccessesActionTypes.GET:
            return {
                accesses: action.payload,
            };
        default:
            return state;
    }
}