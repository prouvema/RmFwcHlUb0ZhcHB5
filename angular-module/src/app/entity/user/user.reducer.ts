import * as UserAction from './user.actions';
import { User } from './user.model';

export type Action = UserAction.All;

// Default app state
const defaultState: User = {
    email: 'axel.aire@phappy',
    firstname: 'Axel',
    lastname: 'Aire'
}

// Helper function to create new state object 
const newState = (state, newData) => {
    return Object.assign({}, state, newData);
}

// Reducer
export function userReducer(state: User = defaultState, action: Action) {
    console.log(action.type, state);

    switch (action.type) {
        case UserAction.POST:
            state = newState(state, action.payload);
            break;
        case UserAction.PATCH:

            break;
        case UserAction.DELETE:

            break;
        default:
            break;
    }

    return state;
}