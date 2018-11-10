import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import { AppRoutingModule } from './app-routing.module';
import { userReducer } from './entity/user/user.reducer';
import { SpectatorWelcomeComponent } from './component/spectator-welcome/spectator-welcome.component';
import { AuthenticationService } from './security/authentication.service';

@NgModule({
  declarations: [
    AppComponent,
    SpectatorWelcomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    StoreModule.forRoot({
      user: userReducer
    })
  ],
  providers: [
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
