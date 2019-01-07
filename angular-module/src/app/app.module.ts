import { BrowserModule } from '@angular/platform-browser';
import { NgModule, TemplateRef, LOCALE_ID } from '@angular/core';
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
import { ErrorInterceptor } from './security/error.interceptor';
import { UnauthorizedComponent } from './component/unauthorized/unauthorized.component';
import { AuthGuardService as AuthGuard } from './security/auth-guard.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatCheckboxModule, MatMenuModule, MatIconModule, MatListModule, MatTableModule, MatSelectModule, MatSlideToggleModule, MatSnackBarModule, MatToolbarModule, MatExpansionModule } from '@angular/material';
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
import { RoleService } from './entity/role/role.service';
import { RolesComponent } from './component/admin/roles/roles.component';
import { SnackBarService } from './shared/snack-bar.service';
import { DashboardSpaceComponent } from './component/dashboard/dashboard-space/dashboard-space.component';
import { SpaceSlotService } from './entity/space-slot/space-slot.service';
import { UrgenceContactsComponent } from './component/dashboard/dashboard-space/urgence-contacts/urgence-contacts.component';
import { MessageService } from './entity/message/message.service';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

registerLocaleData(localeFr, 'fr-FR');

@NgModule({
  declarations: [
    HasAnyRoleDirective,
    AppComponent,
    SpectatorWelcomeComponent,
    DashboardComponent,
    DashboardUserComponent,
    DashboardAdminComponent,
    DashboardSpaceComponent,
    UrgenceContactsComponent,
    UnauthorizedComponent,
    SettingComponent,
    EspacesComponent,
    EspaceEditComponent,
    ReferencesComponent,
    RolesComponent
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
    MatSnackBarModule,
    MatToolbarModule,
    MatExpansionModule,
    
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
    SpaceSlotService,
    ReferenceService,
    RoleService,
    SnackBarService,
    MessageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
    { provide: LOCALE_ID, useValue: "fr-FR" }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
