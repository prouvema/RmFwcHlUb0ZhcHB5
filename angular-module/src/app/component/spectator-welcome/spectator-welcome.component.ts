import { Component, OnInit, OnDestroy } from "@angular/core";
import { Observable, Subscription } from "rxjs";
import { Store } from "@ngrx/store";
import { User } from "src/app/entity/user/user.model";
import { AppState, selectAuthState } from "src/app/app.states";
import { LogIn, LogOut } from "src/app/security/authentication.action";
import { Authentication } from "src/app/security/anthentication.model";
import { AuthenticationService } from "src/app/security/authentication.service";

@Component({
    selector: 'body-spectator-welcome',
    templateUrl: './spectator-welcome.component.html',
    styleUrls: ['./spectator-welcome.component.scss']
})
export class SpectatorWelcomeComponent implements OnInit, OnDestroy {

    public email: string | null;
    public locker: string | null;

    public authState: Observable<any>;
    public authSusbcription: Subscription;

    public errorMessage: string | null;

    public rememberCredentials: boolean;



    constructor(
        private authService: AuthenticationService,
        private store: Store<AppState>
    ) {
        this.authState = this.store.select(selectAuthState);
    }

    ngOnInit() {
        if (this.authService.getToken()) {
            this.store.dispatch(new LogOut);
        }
        this.authSusbcription = this.authState.subscribe((state) => {
            this.errorMessage = state.errorMessage;
        });
    };

    ngOnDestroy(): void {
        this.authSusbcription.unsubscribe();
    }

    public onSubmit(): void {
        const payload: Authentication = {
            username: this.email,
            locker: this.locker
        };
        this.store.dispatch(new LogIn(payload));
    }

}
