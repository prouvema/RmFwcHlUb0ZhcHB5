import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { LightSpace } from "src/app/entity/space/space.model";
import { SpaceService } from "src/app/entity/space/space.service";
import { MatPaginator, PageEvent } from "@angular/material";
import { Router } from "@angular/router";

@Component({
    selector: 'espaces',
    templateUrl: './espaces.component.html',
    styleUrls: ['./espaces.component.scss']
})
export class EspacesComponent implements OnInit, OnDestroy {

    @ViewChild(MatPaginator) paginator: MatPaginator;

    public length = 100;
    public pageSize = 10;
    public pageIndex = 0;

    public displayedColumns: string[] = ['name'];
    public spaces: LightSpace[] = [];

    constructor(
        private spaceService: SpaceService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.getAllSpacesByPageable(this.pageIndex, this.pageSize);
    }

    ngOnDestroy(): void {

    }

    public navigateToSpace(space: LightSpace): void {
        const url = `espaces/edit/${space.id}`;
        this.router.navigateByUrl(url);
    }

    public searchSpaces(event?: PageEvent) {
        this.getAllSpacesByPageable(event.pageIndex, event.pageSize);
        return event;
    }

    protected getAllSpacesByPageable(pageIndex: number, pageSize: number): void {
        this.spaceService.getAllByPageable(pageIndex, pageSize).subscribe(
            response => {
                this.spaces = response.content;
                this.pageIndex = response.pageIndex;
                this.pageSize = response.pageSize;
                this.length = response.totalElements;
            }
        );
    }
}