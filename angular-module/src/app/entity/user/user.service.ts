import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { User } from "./user.model";
import { Injectable } from "@angular/core";
import { API_USER } from "src/app/api.constant";

@Injectable()
export class UserService {

    constructor (
        private http: HttpClient
    ) { }

    // public signUp(email: string, password: string): Observable<User> {
    //     const url = `${API_USER}/register`;
    //     return this.http.post<User>(url, { email, password });
    // }
}