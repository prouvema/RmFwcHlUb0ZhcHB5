import { Component, OnInit, OnDestroy } from "@angular/core";
import { Store } from "@ngrx/store";
import { AppState, selectUserState } from "src/app/app.states";
import { User } from "src/app/entity/user/user.model";
import { Observable, Subscription } from "rxjs";
import { UserService } from "src/app/entity/user/user.service";

@Component({
    selector: 'app-dashboard-user',
    templateUrl: './dashboard-user.component.html',
    styleUrls: ['./dashboard-user.component.scss']
})
export class DashboardUserComponent implements OnInit, OnDestroy {

    public user: User; 

    public userState: Observable<any>;
    public userSubscription: Subscription;

    constructor(
        private userService: UserService,
        private store: Store<AppState>
    ) {
        this.userState = this.store.select(selectUserState);
    }

    ngOnInit() {
        this.userService.loadCurrentUser();
        this.userSubscription = this.userState.subscribe((state) => {
            this.user = state.user;
        });
    };

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();
    }
}