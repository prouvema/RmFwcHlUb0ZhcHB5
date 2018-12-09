import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { Store } from "@ngrx/store";
import { AppState, selectUserState } from "src/app/app.states";
import { User } from "src/app/entity/user/user.model";
import { Observable, Subscription } from "rxjs";
import { UserService } from "src/app/entity/user/user.service";

@Component({
    selector: 'body-dashboard-user',
    templateUrl: './dashboard-user.component.html',
    styleUrls: ['./dashboard-user.component.scss']
})
export class DashboardUserComponent implements OnInit, OnDestroy {

    @Input()
    public user: User;

    constructor() { }

    ngOnInit() {

    };

    ngOnDestroy(): void {

    }
}