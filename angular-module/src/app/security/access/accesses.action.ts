import { Action } from "@ngrx/store";

export enum AccessesActionTypes {
    GET = '[Access] Get',
}

export class GetAccesses implements Action {
    readonly type: AccessesActionTypes = AccessesActionTypes.GET;
    constructor(public payload: string[]) { }
}

export type All =
    | GetAccesses;