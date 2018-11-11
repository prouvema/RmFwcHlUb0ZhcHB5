import * as auth from './security/authentication.reducer';
import { createFeatureSelector } from '@ngrx/store';

export interface AppState {
  authState: auth.State;
}

export const reducers = {
  auth: auth.reducer
};

export const selectAuthState = createFeatureSelector<AppState>('auth');