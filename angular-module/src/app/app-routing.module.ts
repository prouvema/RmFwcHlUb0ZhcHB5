import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';
import { UnauthorizedComponent } from './component/unauthorized/unauthorized.component';
import { AuthGuardService as AuthGuard } from './security/auth-guard.service';
import { DashboardComponent } from './component/dashboard/dashboard.component';

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
    component: DashboardComponent,
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