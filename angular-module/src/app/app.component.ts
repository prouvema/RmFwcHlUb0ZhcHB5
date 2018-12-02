import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState, selectAuthState } from './app.states';
import { IsAuthenticated, LogOut } from './security/authentication.actions';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public authenticated: boolean;
  public authState: Observable<any>;

  constructor(
    private store: Store<AppState>
  ) {
    this.authState = this.store.select(selectAuthState);
  }

  ngOnInit(): void {
    this.store.dispatch(new IsAuthenticated());
    this.authState.subscribe((state) => {
      this.authenticated = state.authenticated;
    });
  }

  public logOut(): void {
    this.store.dispatch(new LogOut);
  }

}
