import { Action } from '@ngrx/store';
import { User } from './user.model';

export const POST: string = '[User] post';
export const PATCH: string = '[User] patch';
export const DELETE: string = '[User] delete';

export class PostUser implements Action {
    readonly type: string = POST;

    constructor(public payload: User) { }

}

export class PatchUser implements Action {
    readonly type: string = PATCH;

    constructor(public payload: User) { }

}

export class DeleteUser implements Action {
    readonly type: string = DELETE;

    constructor(public payload: User) { }

}

export type All = PostUser | PatchUser | DeleteUser;