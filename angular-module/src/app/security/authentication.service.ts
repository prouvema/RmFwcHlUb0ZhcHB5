import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { User } from "../entity/user/user.model";
import { API_AUTH } from "../api.constant";

@Injectable()
export class AuthenticationService {

    constructor(
        private http: HttpClient
    ) { }

    public getToken(): string {
        return localStorage.getItem('token');
    }

    public logIn(email: string, password: string): Observable<any> {
        const url = `${API_AUTH}/login`;
        // return this.http.post<User>(url, { email, password });
        const loginPayload: any = {
            user: {
                email: email,
                firstname: 'Maurice',
                lastname: 'Pantoufle'
            },
            token: 'tooooooooookeeeeeeeeeeeeen'
        }; 
        return of(loginPayload);
    }
}