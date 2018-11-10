import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { User } from "../entity/user/user.model";

const userTest: User = {
    email: 'test@user.bl',
    firstname: 'Maurice',
    lastname: 'Pantoufle'
};

@Injectable()
export class AuthenticationService {

    constructor (
        private httpClient: HttpClient
    ) { }

    public login(username: string, locker: string): Observable<User> {
        return of(userTest);
    }
}