import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { User } from "./user.model";
import { Injectable } from "@angular/core";

@Injectable()
export class UserService {

    constructor (
        private httpClient: HttpClient
    ) { }

    // public postUser(user: User): Observable<User> {
    //     return of(userTest);
        // return this.httpClient.post<User>(API_USER, user);
    // }
}