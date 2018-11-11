import * as UserAction from './user.actions';
import { User } from './user.model';
import { ObjectHelper } from 'src/app/helper/object.helper';

export type Action = UserAction.All;

// Default app state
const defaultState: User = {
    email: 'axel.aire@phappy',
    firstname: 'Axel',
    lastname: 'Aire'
}

// Reducer
export function userReducer(state: User = defaultState, action: Action) {
    console.log(action.type, state);

    switch (action.type) {
        case UserAction.POST:
            state = ObjectHelper.newObject(state, action.payload);
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