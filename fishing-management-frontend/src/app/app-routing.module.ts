import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CatchFormComponent } from './catch-form/catch-form.component';
import { ChangePasswordFormComponent } from './change-password-form/change-password-form.component';
import { FishSpeciesDetailsComponent } from './fish-species-details/fish-species-details.component';
import { FishSpeciesComponent } from './fish-species/fish-species.component';
import { FishingAreaDetailsComponent } from './fishing-area-details/fishing-area-details.component';
import { FishingAreasListComponent } from './fishing-areas/fishing-areas.component';
import { FishingSpotDetailsComponent } from './fishing-spot-details/fishing-spot-details.component';
import { FishingSpotsComponent } from './fishing-spots/fishing-spots.component';
import { FishingAreaGuardService } from './guards/fishing-area-guard.service';
import { KeeperDetailsComponent } from './keeper-details/keeper-details.component';
import { KeeperFormComponent } from './keeper-form/keeper-form.component';
import { KeepersComponent } from './keepers/keepers.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
      { 
        path: "fishing-areas", 
        component: FishingAreasListComponent 
      },
      { 
        path: "fishing-areas/:id", 
        canActivate: [ FishingAreaGuardService ], 
        component: FishingAreaDetailsComponent },
      {
        path: "fish-species",
        component: FishSpeciesComponent
      },
      {
        path: "fish-species-details/:id",
        component: FishSpeciesDetailsComponent
      },
      {
        path: "fishing-spots/:id",
        component: FishingSpotsComponent
      },
      {
        path: "fishing-spot-details/:id",
        component: FishingSpotDetailsComponent
      },
      {
        path: "registration",
        component: RegistrationComponent
      },
      {
        path: "catch-form",
        component: CatchFormComponent
      },
      {
        path: "keeper",
        component: KeeperFormComponent
      },
      {
        path: "keeper/:id",
        component: KeeperDetailsComponent      },
      {
        path: "keepers",
        component: KeepersComponent
      },
      {
        path: "change-password",
        component: ChangePasswordFormComponent
      },
      { 
        path: "", component: LoginComponent 
      },
      { 
        path: "**", 
        redirectTo: "fishing-areas", 
        pathMatch: 'full' 
      }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
