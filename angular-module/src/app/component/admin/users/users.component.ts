import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { MatPaginator, PageEvent } from "@angular/material";
import { UserService } from "src/app/entity/user/user.service";
import { Router } from "@angular/router";
import { User } from "src/app/entity/user/user.model";

@Component({
    selector: 'users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit, OnDestroy {

    @ViewChild(MatPaginator) paginator: MatPaginator;

    public length = 100;
    public pageSize = 10;
    public pageIndex = 0;

    public displayedColumns: string[] = ['username', 'firstname', 'lastname'];
    public users: User[] = [];

    constructor(
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.getAllUsersByPageable(this.pageIndex, this.pageSize);
    }

    ngOnDestroy(): void {

    }

    public navigateToUser(user: User): void {
        const url = `utilisateurs/edit/${user.username}`;
        this.router.navigateByUrl(url);
    }

    public searchUsers(event?: PageEvent) {
        this.getAllUsersByPageable(event.pageIndex, event.pageSize);
        return event;
    }

    protected getAllUsersByPageable(pageIndex: number, pageSize: number): void {
        this.userService.getAllByPageable(pageIndex, pageSize).subscribe(
            response => {
                this.users = response.content;
                this.pageIndex = response.pageIndex;
                this.pageSize = response.pageSize;
                this.length = response.totalElements;
            }
        );
    }
}