import { Component, OnInit } from "@angular/core";

@Component({
    selector: 'app-dashboard-admin',
    templateUrl: './dashboard-admin.component.html',
    styleUrls: ['./dashboard-admin.component.scss']
})
export class DashboardAdminComponent implements OnInit {

    // public user: User; 
    // public userState: Observable<any>;
    
    constructor(
        // private userService: UserService,
        // private store: Store<AppState>
    ) {
        // this.userState = this.store.select(selectUserState);
    }

    ngOnInit() {
        // this.userService.loadCurrentUser();

        // this.userState.subscribe((state) => {
        //     this.user = state.user;
        // });
    };
}