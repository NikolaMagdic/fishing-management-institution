import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FishSpeciesDetailsComponent } from './fish-species-details/fish-species-details.component';
import { FishSpeciesComponent } from './fish-species/fish-species.component';
import { FishingAreaDetailsComponent } from './fishing-area-details/fishing-area-details.component';
import { FishingAreasListComponent } from './fishing-areas/fishing-areas.component';
import { FishingAreaGuardService } from './guards/fishing-area-guard.service';
import { LoginComponent } from './login/login.component';

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
