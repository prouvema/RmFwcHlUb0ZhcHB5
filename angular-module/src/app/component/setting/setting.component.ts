import { Component, OnInit, OnDestroy } from "@angular/core";
import { Observable, Subscription } from "rxjs";
import { UserService } from "src/app/entity/user/user.service";
import { AppState, selectAuthState } from "src/app/app.states";
import { Store } from "@ngrx/store";
import { IsAuthenticated, LogOut } from "src/app/security/authentication.action";

@Component({
    selector: 'app-setting',
    templateUrl: './setting.component.html',
    styleUrls: ['./setting.component.scss']
})
export class SettingComponent implements OnInit, OnDestroy {

    public authenticated: boolean;

    public authState: Observable<any>;
    public authSubscription: Subscription;

    constructor(
        private userService: UserService,
        private store: Store<AppState>
    ) {
        this.authState = this.store.select(selectAuthState);
    }

    ngOnInit(): void {
        this.userService.loadAccesses();
        this.store.dispatch(new IsAuthenticated());
        this.authSubscription = this.authState.subscribe((state) => {
            this.authenticated = state.authenticated;
        });
    }

    ngOnDestroy(): void {
        this.authSubscription.unsubscribe();
    }

    public logOut(): void {
        this.store.dispatch(new LogOut);
    }

}