import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { User } from "src/app/entity/user/user.model";
import { Router } from "@angular/router";
import { SpaceSlot } from "src/app/entity/space-slot/space-slot.model";

@Component({
    selector: 'body-dashboard-user',
    templateUrl: './dashboard-user.component.html',
    styleUrls: ['./dashboard-user.component.scss']
})
export class DashboardUserComponent implements OnInit, OnDestroy {

    @Input()
    public user: User;

    constructor(
        private router: Router
    ) { }

    ngOnInit() {

    };

    ngOnDestroy(): void {

    }

    public navigateToDashboardSpaceBySlot(slot: SpaceSlot): void {
        const url = `/home/space/${slot.space.id}/slot/${slot.id}`;
        this.router.navigateByUrl(url);
    }
}