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
import { AuthGuard } from './guards/auth.guard';
import { FishingAreaGuardService } from './guards/fishing-area-guard.service';
import { KeeperDetailsComponent } from './keeper-details/keeper-details.component';
import { KeeperFormComponent } from './keeper-form/keeper-form.component';
import { KeepersComponent } from './keepers/keepers.component';
import { LicenseRequestsComponent } from './license-requests/license-requests.component';
import { LicenseComponent } from './license/license.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
      { 
        path: "fishing-areas", 
        component: FishingAreasListComponent,
        canActivate: [AuthGuard] 
      },
      { 
        path: "fishing-areas/:id", 
        canActivate: [ FishingAreaGuardService, AuthGuard ], 
        component: FishingAreaDetailsComponent },
      {
        path: "fish-species",
        component: FishSpeciesComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "fish-species-details/:id",
        component: FishSpeciesDetailsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "fishing-spots/:id",
        component: FishingSpotsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "fishing-spot-details/:id",
        component: FishingSpotDetailsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "registration",
        component: RegistrationComponent
      },
      {
        path: "catch-form",
        component: CatchFormComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_FISHERMAN"]}
      },
      {
        path: "keeper",
        component: KeeperFormComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_ADMIN"]}
      },
      {
        path: "keeper/:id",
        component: KeeperDetailsComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_ADMIN", "ROLE_KEEPER"]}      
      },
      {
        path: "keepers",
        component: KeepersComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_ADMIN"]}
      },
      {
        path: "change-password",
        component: ChangePasswordFormComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "license",
        component: LicenseComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_FISHERMAN"]}
      },
      {
        path: "license-requests",
        component: LicenseRequestsComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_ADMIN"]}
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
