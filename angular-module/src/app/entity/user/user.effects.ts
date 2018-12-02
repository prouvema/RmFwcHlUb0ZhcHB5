import { Injectable } from "@angular/core";
import { UserService } from "./user.service";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { GetCurrentUser, UserActionTypes } from "./user.actions";
import { Observable } from "rxjs";
import { switchMap, map, tap } from "rxjs/operators";

@Injectable()
export class AuthenticationEffects {

  constructor(
    private actions: Actions,
    private userService: UserService
  ) { }

  // @Effect()
  // GetCurrentUser: Observable<any> = this.actions.pipe(
  //   ofType(UserActionTypes.GET_CURRENT),
  //   // map((action: LogIn) => action.payload),
  //   switchMap((action) => {
  //     return this.userService.findUserCurrent().pipe(
  //         tap(console.log)
  //     ).subscribe;
  //   //   .pipe(
  //       // map((userResponse) => {
  //       //   console.log('userResponse', userResponse);
  //       //   return new LogInSuccess({ token: loginResponse.access_token });
  //       // }),
  //       // catchError((error) => {
  //       //   console.log('error', error);
  //       //   return of(new LogInFailure({ error: error }));
  //       // })
  //   //   )
  //   })
  // );
  
}