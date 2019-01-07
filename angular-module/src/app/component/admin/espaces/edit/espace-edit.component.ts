import { Component, OnInit, OnDestroy } from "@angular/core";
import { SpaceService } from "src/app/entity/space/space.service";
import { Space, SpaceFactory } from "src/app/entity/space/space.model";
import { ActivatedRoute, Router } from "@angular/router";
import { ReferenceService } from "src/app/entity/reference/reference.service";
import { UserService } from "src/app/entity/user/user.service";
import { RoleService } from "src/app/entity/role/role.service";
import { SnackBarService } from "src/app/shared/snack-bar.service";
import { SpaceSlotFactory } from "src/app/entity/space-slot/space-slot.model";

@Component({
    selector: 'espace-edit',
    templateUrl: './espace-edit.component.html',
    styleUrls: ['./espace-edit.component.scss']
})
export class EspaceEditComponent implements OnInit, OnDestroy {

    public space: Space;
    public familyLinks: string[] = [];
    public usernames: string[] = [];
    public spaceRoles: string[] = [];

    constructor(
        private route: ActivatedRoute,
        private router: Router, 
        private spaceService: SpaceService,
        private referenceService: ReferenceService,
        private userService: UserService,
        private roleService: RoleService,
        private snackBarService: SnackBarService
    ) { }

    ngOnInit(): void {
        this.setSpace_ByParamMap();
        this.setFamilyLinks_ForSelect_WithService();
        this.setUsers_ForSelect_WithService();
        this.setRoles_ForSelect_WithService();
    }    

    ngOnDestroy(): void {

    }

    private setSpace_ByParamMap(): any {
        let spaceId = +this.route.snapshot.paramMap.get('id');
        if (spaceId) {
            this.setSpaceById_WithService(spaceId);
        } else {
            this.space = SpaceFactory.createEmptySpace();
        }
    }

    private setRoles_ForSelect_WithService(): any {
        this.roleService.getAllSpaceRoles_ForSelect().subscribe(spaceRoles => this.spaceRoles = spaceRoles);
    }

    private setUsers_ForSelect_WithService(): void {
        this.userService.getAllUsers_ForSelect().subscribe(usernames => this.usernames = usernames);
    }

    private setFamilyLinks_ForSelect_WithService(): void {
        this.referenceService.getAllFamilyLinks().subscribe(familyLinks => this.familyLinks = familyLinks);
    }

    private setSpaceById_WithService(spaceId: number): void {
        this.spaceService.getSpace(spaceId).subscribe(space => this.space = space);
    }

    public addSpaceSlot(): void {
        this.space.spaceSlots.push(SpaceSlotFactory.createEmptySpaceSlot());
    }

    public onSubmit(): void {
        if (this.space.id) {
            this.spaceService.patchSpace(this.space).subscribe(space => {
                this.space = space;
                this.snackBarService.openSnackBar('Espace : ' + space.name, SnackBarService.SAVED_ACTION);
            });
        } else {
            this.spaceService.postSpace(this.space).subscribe(space => {
                this.router.navigateByUrl(`/espaces/edit/${space.id}`);
                this.snackBarService.openSnackBar('Espace : ' + space.name, SnackBarService.ADDED_ACTION);
            });
        }
    }

    public removeSpaceSlotAtIndex(slotIndex: number): void {
        this.space.spaceSlots.splice(slotIndex, 1);
    }
}