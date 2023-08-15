import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    FormsModule,
    HttpClientModule,
    SharedModule
  ],
  providers: [FishingAreaGuardService],
  bootstrap: [AppComponent]  // Definise pocetnu komponentu aplikacije
})
export class AppModule { }
