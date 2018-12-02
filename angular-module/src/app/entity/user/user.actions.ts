import { Action } from '@ngrx/store';
import { User } from './user.model';

export enum UserActionTypes {
    GET_CURRENT = '[User] get profile'
}

export class GetCurrentUser implements Action {
    readonly type: string = UserActionTypes.GET_CURRENT;
    constructor(public payload?: User) { }
}

export type All =  GetCurrentUser;