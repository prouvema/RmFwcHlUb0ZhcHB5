import * as auth from './security/authentication.reducer';
import * as user from './entity/user/user.reducer';
import * as accesses from './security/access/accesses.reducer';
import { createFeatureSelector } from '@ngrx/store';

export interface AppState {
  authState: auth.State;
  currentUserState: user.State;
  selectAccessesState: accesses.State;
}

export const reducers = {
  auth: auth.reducer,
  user: user.userReducer,
  accesses: accesses.reducer
};

export const selectAuthState = createFeatureSelector<AppState>('auth');
export const selectUserState = createFeatureSelector<AppState>('user');
export const selectAccessesState = createFeatureSelector<AppState>('accesses');