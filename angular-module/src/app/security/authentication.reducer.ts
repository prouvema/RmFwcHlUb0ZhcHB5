import { User } from '../entity/user/user.model';
import { AuthActionTypes, All } from './authentication.actions';

export type Action = All;

export interface State {
    // is a user authenticated?
    isAuthenticated: boolean;
    // if authenticated, there should be a user object
    user: User | null;
    // error message
    errorMessage: string | null;
}

export const initialState: State = {
    isAuthenticated: false,
    user: null,
    errorMessage: null
};

// Reducer
// export function userReducer(state: Authentication, action: Action) {
//     console.log(action.type, state);

//     switch (action.type) {
//         case AuthenticationAction.LOGIN:
//             state = ObjectHelper.newObject(state, action.payload);
//             break;
//         // case UserAction.PATCH:

//         //     break;
//         // case UserAction.DELETE:

//         //     break;
//         default:
//             break;
//     }

//     return state;
// }

export function reducer(state = initialState, action: Action): State {
    switch (action.type) {
        case AuthActionTypes.LOGIN_SUCCESS:
            return {
                ...state,
                isAuthenticated: true,
                user: action.payload.user,
                // token: action.payload.token,
                errorMessage: null
            };
        case AuthActionTypes.LOGIN_FAILURE:
            return {
                ...state,
                errorMessage: 'Incorrect email and/or password.'
            };
        case AuthActionTypes.LOGOUT: 
            return initialState;
        default:
            return state;
    }
}