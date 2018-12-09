import { Directive, Input, OnInit, TemplateRef, ViewContainerRef, OnDestroy } from "@angular/core";
import { Observable, Subscription } from "rxjs";
import { Store } from "@ngrx/store";
import { AppState, selectAccessesState } from "../app.states";

@Directive({
    selector: '[hasAnyRole]'
})
export class HasAnyRoleDirective implements OnInit, OnDestroy {

    @Input('hasAnyRole') hasAnyRole: string | string[];

    public accessesState: Observable<any>;
    public accessesSubscription: Subscription;

    public previousAccesses: string[] = [];

    constructor(
        private store: Store<AppState>,
        private templateRef: TemplateRef<any>,
        private viewContainer: ViewContainerRef
    ) {
        this.accessesState = this.store.select(selectAccessesState);
    }

    ngOnInit(): void {
        this.accessesSubscription = this.accessesState.subscribe((state) => {
            // if (!this.arraysAreEqual(this.previousAccesses, state.accesses)) {
            //     this.previousAccesses = state.accesses;
                this.applyPermission(state.accesses);
            // }
        });
    }

    ngOnDestroy(): void {
        this.accessesSubscription.unsubscribe();
    }

    private applyPermission(accesses: string[]): void {
        if (this.hasPermission(accesses)) {
            this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
            this.viewContainer.clear();
        }
    }

    public hasPermission(accesses: string[]): boolean {
        if (!accesses.length) {
            return false;
        }
        let hasPermission = false;
        if (typeof this.hasAnyRole === 'string') {
            hasPermission = accesses.includes(this.hasAnyRole);
        } else if (this.hasAnyRole instanceof Array) {
            hasPermission = this.hasAnyRole.filter(role => accesses.includes(role)).length != 0;
        }
        return hasPermission;
    }

    // private arraysAreEqual(a0: string[], a1: string[]) {
    //     if (a0.length !== a1.length) {
    //         return false;
    //     }
    //     return !a0.filter(str => !a1.includes(str)).length;
    // }
}