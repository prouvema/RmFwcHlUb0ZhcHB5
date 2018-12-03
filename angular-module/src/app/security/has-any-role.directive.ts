import { Directive, Input, OnInit, TemplateRef, ViewContainerRef } from "@angular/core";
import { Observable } from "rxjs";
import { UserService } from "../entity/user/user.service";
import { Store } from "@ngrx/store";
import { AppState, selectUserState } from "../app.states";
import { User } from "../entity/user/user.model";

@Directive({
    selector: '[hasAnyRole]'
})
export class HasAnyRoleDirective implements OnInit {

    @Input('hasAnyRole') hasAnyRole: string | string[];
    public userState: Observable<any>;

    constructor(
        private userService: UserService,
        private store: Store<AppState>,
        private templateRef: TemplateRef<any>,
        private viewContainer: ViewContainerRef
    ) {
        this.userState = this.store.select(selectUserState);
    }

    ngOnInit(): void {
        const user: User = this.userService.getCurrentUser();
        this.applyPermission(user);
        this.userState.subscribe((state) => {
            this.applyPermission(state.user);
        });
    }

    private applyPermission(user: User): void {
        if (this.hasPermission(user)) {
            this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
            this.viewContainer.clear();
        }
    }

    public hasPermission(user: User): boolean {
        if (!user) {
            return false;
        }
        let hasPermission = false;
        if (typeof this.hasAnyRole === 'string') {
            hasPermission = user.roles.includes(this.hasAnyRole);
        } else if (this.hasAnyRole instanceof Array) {
            hasPermission = this.hasAnyRole.filter(role => user.roles.includes(role)).length != 0;
        }
        console.log('hasPermission', hasPermission);
        return hasPermission;
    }
}