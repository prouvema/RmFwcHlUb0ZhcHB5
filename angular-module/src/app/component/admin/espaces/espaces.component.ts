import { Component, OnInit, OnDestroy } from "@angular/core";
import { LightSpace } from "src/app/entity/space/space.model";
import { SpaceService } from "src/app/entity/space/space.service";

@Component({
    selector: 'espaces',
    templateUrl: './espaces.component.html',
    styleUrls: ['./espaces.component.scss']
})
export class EspacesComponent implements OnInit, OnDestroy {

    public displayedColumns: string[] = ['name'];
    public spaces: LightSpace[] = [];

    constructor(
        private spaceService: SpaceService
    ) { }

    ngOnInit(): void {
        this.spaceService.getAllSpaces().subscribe(spaces => this.spaces = spaces);
    }

    ngOnDestroy(): void {
        
    }

}