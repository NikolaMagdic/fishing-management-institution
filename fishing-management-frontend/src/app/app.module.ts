import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FishingAreasListComponent } from './fishing-areas/fishing-areas.component';
import { FishingAreaDetailsComponent } from './fishing-area-details/fishing-area-details.component';
import { LoginComponent } from './login/login.component';
import { FishingAreaGuardService } from './guards/fishing-area-guard.service';
import { SharedModule } from './shared/shared.module';
import { FishSpeciesComponent } from './fish-species/fish-species.component';
import { FishSpeciesDetailsComponent } from './fish-species-details/fish-species-details.component';
import { RegistrationComponent } from './registration/registration.component';
import { FishingSpotsComponent } from './fishing-spots/fishing-spots.component';
import { FishingSpotDetailsComponent } from './fishing-spot-details/fishing-spot-details.component';
import { CatchFormComponent } from './catch-form/catch-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { KeeperDetailsComponent } from './keeper-details/keeper-details.component';
import { KeepersComponent } from './keepers/keepers.component';
import { KeeperFormComponent } from './keeper-form/keeper-form.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { ChangePasswordFormComponent } from './change-password-form/change-password-form.component';
import { LicenseComponent } from './license/license.component';
import { LicenseRequestsComponent } from './license-requests/license-requests.component';

@NgModule({
  declarations: [
    AppComponent,
    FishingAreasListComponent,
    FishingAreaDetailsComponent,
    LoginComponent,
    FishSpeciesComponent,
    FishSpeciesDetailsComponent,
    RegistrationComponent,
    FishingSpotsComponent,
    FishingSpotDetailsComponent,
    CatchFormComponent,
    KeeperDetailsComponent,
    KeepersComponent,
    KeeperFormComponent,
    ChangePasswordFormComponent,
    LicenseComponent,
    LicenseRequestsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    FormsModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
  ],
  providers: [
    FishingAreaGuardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]  // Definise pocetnu komponentu aplikacije
})
export class AppModule { }
