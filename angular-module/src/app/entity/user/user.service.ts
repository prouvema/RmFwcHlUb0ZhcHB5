import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { User } from "./user.model";
import { Injectable } from "@angular/core";
import { API_USER } from "src/app/api.constant";
import { UserActionTypes } from "./user.actions";
import { Store } from "@ngrx/store";
import { AppState } from "src/app/app.states";
import { map } from "rxjs/operators";

@Injectable()
export class UserService {

    private user: User;

    constructor(
        private http: HttpClient,
        private store: Store<AppState>
    ) { }

    // public signUp(email: string, password: string): Observable<User> {
    //     const url = `${API_USER}/register`;
    //     return this.http.post<User>(url, { email, password });
    // }

    public getCurrentUser(): User {
        return JSON.parse(localStorage.getItem('user'));
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
                localStorage.setItem('user', JSON.stringify(action.payload.user));
                this.store.dispatch(action);
            });;
    }
}