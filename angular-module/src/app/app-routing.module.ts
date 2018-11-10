import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';

const routes: Routes = [{
  path: 'welcome',
  component: SpectatorWelcomeComponent,
  // children: [{
  //   path: 'legacy/user/:name',
  //   redirectTo: 'user/:name'
  // }, {
  //   path: 'user/:name',
  //   component: User
  // }]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }