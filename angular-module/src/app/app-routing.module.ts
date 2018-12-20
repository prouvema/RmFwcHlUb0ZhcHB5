import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';
import { UnauthorizedComponent } from './component/unauthorized/unauthorized.component';
import { AuthGuardService as AuthGuard } from './security/auth-guard.service';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { EspacesComponent } from './component/admin/espaces/espaces.component';
import { EspaceEditComponent } from './component/admin/espaces/edit/espace-edit.component';
import { ReferencesComponent } from './component/admin/references/references.component';

const routes: Routes = [
  { path: 'welcome', component: SpectatorWelcomeComponent,
    // children: [{
    //   path: 'legacy/user/:name',
    //   redirectTo: 'user/:name'
    // }, {
    //   path: 'user/:name',
    //   component: User
    // }]
  },
  { path: 'home', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'espaces', component: EspacesComponent, canActivate: [AuthGuard] },
  { path: 'espaces/edit', component: EspaceEditComponent, canActivate: [AuthGuard] },
  { path: 'espaces/edit/:id', component: EspaceEditComponent, canActivate: [AuthGuard] },
  { path: 'references', component: ReferencesComponent, canActivate: [AuthGuard] },
  { path: 'unauthorized', component: UnauthorizedComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'welcome' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }