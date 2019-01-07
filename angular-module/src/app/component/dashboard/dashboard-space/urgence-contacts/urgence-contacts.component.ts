import { Component, OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { User } from "src/app/entity/user/user.model";
import { UserService } from "src/app/entity/user/user.service";

@Component({
    selector: 'body-urgence-contacts',
    templateUrl: './urgence-contacts.component.html',
    styleUrls: ['./urgence-contacts.component.scss']
})
export class UrgenceContactsComponent implements OnInit, OnDestroy {

    public urgenceContacts: User[] = [];

    constructor(
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit() {
        let spaceId = +this.route.snapshot.paramMap.get('spaceId');
        this.userService.getAllUrgenceContactsBySpaceId(spaceId).subscribe(urgenceContacts => this.urgenceContacts = urgenceContacts);
    };

    ngOnDestroy(): void {
        
    }

    public navigateToDashboardSpace(): void {
        let spaceId = +this.route.snapshot.paramMap.get('spaceId');
        let slotId = +this.route.snapshot.paramMap.get('slotId');
        const url = `/home/space/${spaceId}/slot/${slotId}`;
        this.router.navigateByUrl(url);
    }

}
