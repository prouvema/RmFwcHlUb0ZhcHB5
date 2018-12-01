import { User } from '../entity/user/user.model';
import { AuthActionTypes, All } from './authentication.actions';

export type Action = All;

export interface State {
    // is a user authenticated?
    isAuthenticated: boolean;
    // if authenticated, there should be a user object
    token: string | null;
    // error message
    errorMessage: string | null;
}

export const initialState: State = {
    isAuthenticated: false,
    token: null,
    // expires_in: 3423
    // refresh_token: "e1016231-53a3-4bab-83b2-30fbd97439cd"
    // scope: "read write trust"
    // token_type: "bearer",
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
        console.log('yep');
        console.log(JSON.stringify(action.payload));
            return {
                ...state,
                isAuthenticated: true,
                token: action.payload.token,
                // token: action.payload.token,
                errorMessage: null
            };
        case AuthActionTypes.LOGIN_FAILURE:
            return {
                ...state,
                errorMessage: 'Incorrect email et/ou mot de passe.'
            };
        case AuthActionTypes.LOGOUT: 
            return initialState;
        default:
            return state;
    }
}