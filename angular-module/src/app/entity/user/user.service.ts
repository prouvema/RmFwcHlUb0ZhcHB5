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

    constructor(
        private http: HttpClient,
        private store: Store<AppState>
    ) { }

    // public signUp(email: string, password: string): Observable<User> {
    //     const url = `${API_USER}/register`;
    //     return this.http.post<User>(url, { email, password });
    // }


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
            // .map((res: Response) => {
            //     let body = res.json();
            //     return body.data || {};
            // })
            // .map((user: any) => {
            //     return { type: UserActionTypes.GET_CURRENT, user };
            // })
            .subscribe((action) => {
                this.store.dispatch(action);
            });;
    }
}