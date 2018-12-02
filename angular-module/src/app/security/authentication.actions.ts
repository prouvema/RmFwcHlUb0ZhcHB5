import { Action } from "@ngrx/store";
import { Authentication } from "./anthentication.model";

export enum AuthActionTypes {
    LOGIN = '[Auth] Login',
    LOGIN_SUCCESS = '[Auth] Login Success',
    LOGIN_FAILURE = '[Auth] Login Failure',
    LOGOUT = '[Auth] LogOut',
    IS_AUTHENTICATED = '[Auth] Is Authenticated',
    AUTHENTICATED = '[Auth] Authenticated'
}

export class LogIn implements Action {
    readonly type: AuthActionTypes = AuthActionTypes.LOGIN;
    constructor(public payload: Authentication) { }
}

export class LogInSuccess implements Action {
    readonly type: AuthActionTypes = AuthActionTypes.LOGIN_SUCCESS;
    constructor(public payload: any) { }
}

export class LogInFailure implements Action {
    readonly type: AuthActionTypes = AuthActionTypes.LOGIN_FAILURE;
    constructor(public payload: any) { }
}

export class LogOut implements Action {
    readonly type: AuthActionTypes = AuthActionTypes.LOGOUT;
    constructor(public payload?: any) { }
}

export class IsAuthenticated implements Action {
    readonly type: AuthActionTypes = AuthActionTypes.IS_AUTHENTICATED;
    constructor(public payload?: any) { }
}

export class Authenticated implements Action {
    readonly type: AuthActionTypes = AuthActionTypes.AUTHENTICATED;
    constructor(public payload?: any) { }
}

export type All =
    | LogIn
    | LogInSuccess
    | LogInFailure
    | LogOut
    | IsAuthenticated
    | Authenticated;