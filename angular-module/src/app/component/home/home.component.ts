import { Component, OnInit } from "@angular/core";
import { Store } from "@ngrx/store";
import { AppState, selectAuthState } from "src/app/app.states";
import { LogOut } from "src/app/security/authentication.actions";
import { User } from "src/app/entity/user/user.model";
import { Observable } from "rxjs";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

    public user: User; 
    public getState: Observable<any>;
    
    constructor(
        private store: Store<AppState>
    ) {
        this.getState = this.store.select(selectAuthState);
    }

    ngOnInit() {
        this.getState.subscribe((state) => {
            this.user = state.user;
        });
    };

    public logOut(): void {
        this.store.dispatch(new LogOut);
    }
}