import { Component, OnInit, OnDestroy } from "@angular/core";
import { RoleService } from "src/app/entity/role/role.service";
import { SpaceRole } from "src/app/entity/role/role.model";
import { SnackBarService } from "src/app/shared/snack-bar.service";

@Component({
    selector: 'roles',
    templateUrl: './roles.component.html',
    styleUrls: ['./roles.component.scss']
})
export class RolesComponent implements OnInit, OnDestroy {

    public spaceAccesses: string[] = [];
    public spaceRoles: SpaceRole[] = [];
    public spaceRole: SpaceRole = {
        id: 0,
        name: null,
        accesses: []
    };

    constructor(
        private roleService: RoleService,
        private snackBarService: SnackBarService
    ) { }

    ngOnInit(): void {
        this.getSpaceRoles_WithService();
        this.getSpaceAccesses_WithService();
    }

    ngOnDestroy(): void {

    }

    public addSpaceRole(): void {
        this.roleService.postSpaceRole(this.spaceRole).subscribe(spaceRole => {
            this.spaceRoles.push(spaceRole);
            this.snackBarService.openSnackBar("Role : " + spaceRole.name, SnackBarService.ADDED_ACTION);
        });
    }

    private getSpaceAccesses_WithService(): void {
        this.roleService.getAllSpaceAccesses_ForSelect().subscribe(spaceAccesses => this.spaceAccesses = spaceAccesses);
    }

    private getSpaceRoles_WithService(): void {
        this.roleService.getAllSpaceRoles().subscribe(spaceRoles => this.spaceRoles = spaceRoles);
    }

}