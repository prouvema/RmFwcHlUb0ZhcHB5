import { User } from '../entity/user/user.model';
import { AuthActionTypes, All } from './authentication.actions';

export type Action = All;

export interface State {
    isAuthenticated: boolean;
    token: string | null;
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

export function reducer(state = initialState, action: Action): State {
    switch (action.type) {
        case AuthActionTypes.LOGIN_SUCCESS:
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