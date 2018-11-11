import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';
import { HomeComponent } from './component/home/home.component';
import { UnauthorizedComponent } from './component/unauthorized/unauthorized.component';
import { AuthGuardService as AuthGuard } from './security/auth-guard.service';

const routes: Routes = [
  {
    path: 'welcome',
    component: SpectatorWelcomeComponent,
    // children: [{
    //   path: 'legacy/user/:name',
    //   redirectTo: 'user/:name'
    // }, {
    //   path: 'user/:name',
    //   component: User
    // }]
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'unauthorized',
    component: UnauthorizedComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: 'welcome'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }