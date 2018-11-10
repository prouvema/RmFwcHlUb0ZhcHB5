import { Component } from "@angular/core";
import { Observable } from "rxjs";
import { Store } from "@ngrx/store";

interface AppState {
    message: string;
}

@Component({
    selector: 'app-spectator-welcome',
    templateUrl: './spectator-welcome.component.html'
})
export class SpectatorWelcomeComponent {

    public title = 'Hello';
    public _message: Observable<string>

    constructor(private store: Store<AppState>) {
        this._message = this.store.select('message');
    }

    // postUser() {
    //     this.store.dispatch({ type: 'POST' });
    // }

    // patchUser() {
    //     this.store.dispatch({ type: 'PATCH' });
    // }
}