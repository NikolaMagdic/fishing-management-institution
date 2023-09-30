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
import { FishermanDetailsComponent } from './fisherman-details/fisherman-details.component';
import { CatchesComponent } from './catches/catches.component';
import { FishermansComponent } from './fishermans/fishermans.component';
import { FishStockingComponent } from './fish-stocking/fish-stocking.component';
import { PenaltiesComponent } from './penalties/penalties.component';
import { PenaltyDetailsComponent } from './penalty-details/penalty-details.component';
import { PenaltiesOfFishermanComponent } from './penalties-of-fisherman/penalties-of-fisherman.component';
import { FishSpeciesPipe } from './shared/fish-species.pipe';

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
    FishermanDetailsComponent,
    CatchesComponent,
    FishermansComponent,
    FishStockingComponent,
    PenaltiesComponent,
    PenaltyDetailsComponent,
    PenaltiesOfFishermanComponent,
    FishSpeciesPipe
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
