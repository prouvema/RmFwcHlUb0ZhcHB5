import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
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

    // public logIn(email: string, password: string): Observable<any> {
    //     const url = `${API_AUTH}/login`;
    //     // return this.http.post<User>(url, { email, password });
    //     const loginPayload: any = {
    //         user: {
    //             email: email,
    //             firstname: 'Maurice',
    //             lastname: 'Pantoufle'
    //         },
    //         token: 'tooooooooookeeeeeeeeeeeeen'
    //     };
    //     return of(loginPayload);
    // }

    /**
    * Get token from rest api
    * @param email
    * @param locker
    */
    public logIn(email: string, locker: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders(
                { 'Content-type': 'application/x-www-form-urlencoded', 'Authorization': 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0' })
        };
        // base 64 encoding of user locker
        const b64locker = btoa(locker);
        const body = `grant_type=password&username=${email}&password=${b64locker}&client_id=my-trusted-client`;

        const url = `${API_AUTH}/token`;
        console.log('url', url);
        console.log('body', body);
        
        return this.http.post<any>(url, body, httpOptions);
    }

}