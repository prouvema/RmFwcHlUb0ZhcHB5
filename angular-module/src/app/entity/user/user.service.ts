import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { User } from "./user.model";
import { Injectable } from "@angular/core";
import { API_USER } from "src/app/api.constant";
import { UserActionTypes } from "./user.actions";
import { Store } from "@ngrx/store";
import { AppState } from "src/app/app.states";
import { map } from "rxjs/operators";
import { AccessesActionTypes } from "src/app/security/access/accesses.action";

@Injectable()
export class UserService {

    constructor(
        private http: HttpClient,
        private store: Store<AppState>
    ) { }

    public getAllUsers(): Observable<User[]> {
        const url = `${API_USER}`;
        return this.http.get<User[]>(url);
    }

    public loadCurrentUser() {
        const url = `${API_USER}/current`;
        return this.http.get<User>(url)
            .pipe(
                map((user: User) => {
                    return {
                        type: UserActionTypes.GET_CURRENT,
                        payload: {
                            user: user
                        }
                    };
                })
            )
            .subscribe((action) => {
                // localStorage.setItem('user', JSON.stringify(action.payload.user));
                this.store.dispatch(action);
            });
    }

    public loadAccesses(): void {
        const url = `${API_USER}/accesses`;
        this.http.get<string[]>(url)
            .pipe(
                map((accesses: string[]) => {
                    return {
                        type: AccessesActionTypes.GET,
                        payload: accesses
                    };
                })
            )
            .subscribe((action) => {
                this.store.dispatch(action);
            });
    }
}