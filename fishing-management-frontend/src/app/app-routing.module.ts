import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CatchFormComponent } from './catch-form/catch-form.component';
import { CatchesComponent } from './catches/catches.component';
import { ChangePasswordFormComponent } from './change-password-form/change-password-form.component';
import { FishSpeciesDetailsComponent } from './fish-species-details/fish-species-details.component';
import { FishSpeciesComponent } from './fish-species/fish-species.component';
import { FishStockingComponent } from './fish-stocking/fish-stocking.component';
import { FishermanDetailsComponent } from './fisherman-details/fisherman-details.component';
import { FishermansComponent } from './fishermans/fishermans.component';
import { FishingAreaCatchesComponent } from './fishing-area-catches/fishing-area-catches.component';
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
import { PenaltiesOfFishermanComponent } from './penalties-of-fisherman/penalties-of-fisherman.component';
import { PenaltiesComponent } from './penalties/penalties.component';
import { PenaltyDetailsComponent } from './penalty-details/penalty-details.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReservationsComponent } from './reservations/reservations.component';

const routes: Routes = [
      { 
        path: "fishing-areas", 
        component: FishingAreasListComponent,
        canActivate: [AuthGuard] 
      },
      { 
        path: "fishing-areas/:id", 
        canActivate: [ FishingAreaGuardService, AuthGuard ], 
        component: FishingAreaDetailsComponent 
      },
      {
        path: "fishing-areas/:id/child",
        component: FishingAreasListComponent,
        canActivate: [AuthGuard]
      },
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
        path: "fishing-spot-details/area/:areaId/spot/:spotId",
        component: FishingSpotDetailsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "registration",
        component: RegistrationComponent
      },
      {
        path: "fishermans",
        component: FishermansComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "fisherman/:id",
        component: FishermanDetailsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "catch-form",
        component: CatchFormComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_FISHERMAN"]}
      },
      {
        path: "fisherman/:id/catches",
        component: CatchesComponent,
        canActivate: [AuthGuard]
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
        path: "reservations/:id",
        component: ReservationsComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_FISHERMAN"]}
      },
      {
        path: "reservations/:areaId/:spotId",
        component: ReservationsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "fish-stocking",
        component: FishStockingComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_KEEPER", "ROLE_ADMIN"]}
      },
      {
        path: "penalties",
        component: PenaltiesComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "penalty/:id",
        component: PenaltyDetailsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "fisherman/:id/penalties",
        component: PenaltiesOfFishermanComponent,
        canActivate: [AuthGuard]
      },
      {
        path: "area-catches/:id",
        component: FishingAreaCatchesComponent,
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
