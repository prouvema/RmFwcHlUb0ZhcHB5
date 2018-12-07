import { Component, OnInit, OnDestroy } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable, Subscription } from 'rxjs';
import { AppState, selectAuthState } from './app.states';
import { IsAuthenticated, LogOut } from './security/authentication.action';
import { UserService } from './entity/user/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  public authenticated: boolean;

  public authState: Observable<any>;
  public authSubscription: Subscription;

  constructor(
    private userService: UserService,
    private store: Store<AppState>
  ) {
    this.authState = this.store.select(selectAuthState);
  }

  ngOnInit(): void {
    this.userService.loadAccesses();
    this.store.dispatch(new IsAuthenticated());
    this.authSubscription = this.authState.subscribe((state) => {
      this.authenticated = state.authenticated;
    });
  }

  ngOnDestroy(): void {
    this.authSubscription.unsubscribe();
  }

  public logOut(): void {
    this.store.dispatch(new LogOut);
  }

}
