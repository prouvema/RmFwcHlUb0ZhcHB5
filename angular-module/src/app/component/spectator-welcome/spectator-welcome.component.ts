import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Store } from "@ngrx/store";
import { User } from "src/app/entity/user/user.model";
import { AppState, selectAuthState } from "src/app/app.states";
import { LogIn } from "src/app/security/authentication.actions";
import { Authentication } from "src/app/security/anthentication.model";

@Component({
    selector: 'app-spectator-welcome',
    templateUrl: './spectator-welcome.component.html'
})
export class SpectatorWelcomeComponent implements OnInit {

    public email: string | null;
    public locker: string | null;
    public getState: Observable<any>;
    public errorMessage: string | null;

    constructor(
        private store: Store<AppState>
    ) {
        this.getState = this.store.select(selectAuthState);
    }

    ngOnInit() {
        this.getState.subscribe((state) => {
            this.errorMessage = state.errorMessage;
        });
    };

    public onSubmit(): void {
        console.log(this.email);
        const payload: Authentication = {
            username: this.email,
            locker: this.locker
        };
        this.store.dispatch(new LogIn(payload));
    }

}
