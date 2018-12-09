import { Component, OnInit, Input } from "@angular/core";
import { User } from "src/app/entity/user/user.model";

@Component({
    selector: 'body-dashboard-admin',
    templateUrl: './dashboard-admin.component.html',
    styleUrls: ['./dashboard-admin.component.scss']
})
export class DashboardAdminComponent implements OnInit {

    @Input()
    public user: User;
    
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