import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Observable, of } from "rxjs";
import { catchError, map, switchMap, tap } from "rxjs/operators";
import { AuthenticationService } from './authentication.service';
import { AuthActionTypes, LogIn, LogInSuccess, LogInFailure, LogOut } from './authentication.actions';

@Injectable()
export class AuthenticationEffects {

  constructor(
    private actions: Actions,
    private authService: AuthenticationService,
    private router: Router
  ) { }

  @Effect()
  LogIn: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGIN),
    map((action: LogIn) => action.payload),
    switchMap((payload) => {
      return this.authService.logIn(payload.username, payload.locker).pipe(
        map((loginResponse) => {
          console.log('loginResponse', loginResponse);
          return new LogInSuccess({ token: loginResponse.token, user: loginResponse.user });
        }),
        catchError((error) => {
          console.log('error', error);
          return of(new LogInFailure({ error: error }));
        })
      )
    })
  );

  @Effect({ dispatch: false })
  LogInSuccess: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGIN_SUCCESS),
    tap((action) => {
      localStorage.setItem('token', action.payload.token);
      this.router.navigateByUrl('/home');
    })
  );

  @Effect({ dispatch: false })
  LogInFailure: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGIN_FAILURE)
  );

  @Effect({ dispatch: false })
  LogOut: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGOUT),
    tap(() => {
      localStorage.removeItem('token');
      this.router.navigateByUrl('/welcome');
    })
  );
}