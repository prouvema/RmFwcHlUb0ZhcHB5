import { BrowserModule } from '@angular/platform-browser';
import { NgModule, TemplateRef } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import { AppRoutingModule } from './app-routing.module';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';
import { AuthenticationService } from './security/authentication.service';
import { EffectsModule } from '@ngrx/effects';
import { AuthenticationEffects } from './security/authentication.effect';
import { reducers } from './app.states';
import { TokenInterceptor } from './security/token.interceptor';
import { UnauthorizedInterceptor } from './security/unauthorized.interceptor';
import { UnauthorizedComponent } from './component/unauthorized/unauthorized.component';
import { AuthGuardService as AuthGuard } from './security/auth-guard.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatCheckboxModule, MatMenuModule, MatIconModule, MatListModule, MatTableModule, MatSelectModule, MatSlideToggleModule } from '@angular/material';
import { UserService } from './entity/user/user.service';
import { HasAnyRoleDirective } from './directive/has-any-role.directive';
import { DashboardAdminComponent } from './component/dashboard/dashboard-admin/dashboard-admin.component';
import { DashboardUserComponent } from './component/dashboard/dashboard-user/dashboard-user.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { SettingComponent } from './component/setting/setting.component';
import { EspacesComponent } from './component/admin/espaces/espaces.component';
import { SpaceService } from './entity/space/space.service';
import { EspaceEditComponent } from './component/admin/espaces/edit/espace-edit.component';
import { ReferencesComponent } from './component/admin/references/references.component';
import { ReferenceService } from './entity/reference/reference.service';

@NgModule({
  declarations: [
    HasAnyRoleDirective,
    AppComponent,
    SpectatorWelcomeComponent,
    DashboardComponent,
    DashboardUserComponent,
    DashboardAdminComponent,
    UnauthorizedComponent,
    SettingComponent,
    EspacesComponent,
    EspaceEditComponent,
    ReferencesComponent
  ],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule, 
    MatCheckboxModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatSelectModule,
    MatSlideToggleModule,
    
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    EffectsModule.forRoot([AuthenticationEffects]),
    // StoreModule.forRoot({
    //   user: userReducer
    // })
    StoreModule.forRoot(reducers, {}),
    BrowserAnimationsModule
  ],
  providers: [
    AuthenticationService,
    AuthGuard,
    UserService,
    SpaceService,
    ReferenceService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: UnauthorizedInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
