import * as auth from './security/authentication.reducer';
import * as user from './entity/user/user.reducer';
import { createFeatureSelector } from '@ngrx/store';

export interface AppState {
  authState: auth.State;
  currentUserState: user.State; 
}

export const reducers = {
  auth: auth.reducer,
  user: user.userReducer
};

export const selectAuthState = createFeatureSelector<AppState>('auth');
export const selectUserState = createFeatureSelector<AppState>('user');