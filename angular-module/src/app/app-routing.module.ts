import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';
import { UnauthorizedComponent } from './component/unauthorized/unauthorized.component';
import { AuthGuardService as AuthGuard } from './security/auth-guard.service';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { EspacesComponent } from './component/admin/espaces/espaces.component';
import { EspaceEditComponent } from './component/admin/espaces/edit/espace-edit.component';
import { ReferencesComponent } from './component/admin/references/references.component';
import { RolesComponent } from './component/admin/roles/roles.component';
import { DashboardSpaceComponent } from './component/dashboard/dashboard-space/dashboard-space.component';
import { UrgenceContactsComponent } from './component/dashboard/dashboard-space/urgence-contacts/urgence-contacts.component';
import { UsersComponent } from './component/admin/users/users.component';
import { UserEditComponent } from './component/admin/users/edit/user-edit.component';

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
  { path: 'home/space/:spaceId/slot/:slotId', component: DashboardSpaceComponent, canActivate: [AuthGuard] },
  { path: 'home/space/:spaceId/slot/:slotId/urgencecontacts', component: UrgenceContactsComponent, canActivate: [AuthGuard] },
  { path: 'espaces', component: EspacesComponent, canActivate: [AuthGuard] },
  { path: 'espaces/edit', component: EspaceEditComponent, canActivate: [AuthGuard] },
  { path: 'espaces/edit/:id', component: EspaceEditComponent, canActivate: [AuthGuard] },
  { path: 'utilisateurs', component: UsersComponent, canActivate: [AuthGuard] },
  { path: 'utilisateurs/edit', component: UserEditComponent, canActivate: [AuthGuard] },
  { path: 'utilisateurs/edit/:id', component: UserEditComponent, canActivate: [AuthGuard] },
  { path: 'references', component: ReferencesComponent, canActivate: [AuthGuard] },
  { path: 'roles', component: RolesComponent, canActivate: [AuthGuard] },
  { path: 'unauthorized', component: UnauthorizedComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'welcome' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }