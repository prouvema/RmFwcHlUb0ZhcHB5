import { User } from './user.model';
import { UserActionTypes, All } from './user.actions';

export type Action = All;

export interface State {
    user: User | null;
}

// Default app state
const initialState: State = {
    user: null
}

// Reducer
export function userReducer(state: State = initialState, action: Action) {
    // console.log(action.type, state);

    switch (action.type) {
        // case UserAction.POST:
           
        // case UserAction.PATCH:

        // case UserAction.DELETE:

        case UserActionTypes.GET_CURRENT:
            return action.payload;
        default:
            return state;
    }
}