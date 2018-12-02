import { Component, OnInit } from "@angular/core";
import { Store } from "@ngrx/store";
import { AppState, selectUserState } from "src/app/app.states";
import { LogOut } from "src/app/security/authentication.actions";
import { User } from "src/app/entity/user/user.model";
import { Observable } from "rxjs";
import { GetCurrentUser } from "src/app/entity/user/user.actions";
import { UserService } from "src/app/entity/user/user.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

    public user: User; 
    public userState: Observable<any>;
    
    constructor(
        private userService: UserService,
        private store: Store<AppState>
    ) {
        this.userState = this.store.select(selectUserState);
    }

    ngOnInit() {
        this.userService.loadCurrentUser();

        // this.store.dispatch(new GetCurrentUser);
        this.userState.subscribe((state) => {
            this.user = state.user;
        });
    };

    public logOut(): void {
        this.store.dispatch(new LogOut);
    }
}