import { Component, OnInit, OnDestroy } from "@angular/core";
import { SpaceService } from "src/app/entity/space/space.service";
import { SpaceSlot, Space } from "src/app/entity/space/space.model";
import { ActivatedRoute } from "@angular/router";
import { ReferenceService } from "src/app/entity/reference/reference.service";
import { User } from "src/app/entity/user/user.model";
import { UserService } from "src/app/entity/user/user.service";

@Component({
    selector: 'espace-edit',
    templateUrl: './espace-edit.component.html',
    styleUrls: ['./espace-edit.component.scss']
})
export class EspaceEditComponent implements OnInit, OnDestroy {

    public space: Space;
    public familyLinks: string[] = [];
    public users: User[] = [];

    constructor(
        private route: ActivatedRoute,
        private spaceService: SpaceService,
        private referenceService: ReferenceService,
        private userService: UserService
    ) { }

    ngOnInit(): void {
        let id = +this.route.snapshot.paramMap.get('id');
        if (id) {
            this.spaceService.getSpace(id).subscribe(space => this.space = space);
        } else {
            this.space = {
                id: 0,
                name: null,
                spaceSlots: []
            }
        }
        this.referenceService.getAllFamilyLinks().subscribe(familyLinks => this.familyLinks = familyLinks);
        this.userService.getAllUsers().subscribe(users => this.users = users);
        // this.spaceService.getAllSpaces().subscribe(spaces => this.spaces = spaces);
    }

    ngOnDestroy(): void {

    }

    public addSpaceSlot(): void {
        this.space.spaceSlots.push({
            id: 0,
            familyLink: null,
            tokenValidation: null,
            urgenceContact: false,
            user: null,
            validationState: 'WAITING',
            roles: []
        });
    }

    public onSubmit(): void {
        console.log('space', this.space);
        if (this.space.id) {
            this.spaceService.patchSpace(this.space).subscribe(space => this.space = space);
        } else {
            this.spaceService.postSpace(this.space).subscribe(space => this.space = space);
        }
    }
}