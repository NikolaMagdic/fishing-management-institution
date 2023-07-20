import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FishingAreasListComponent } from './fishing-areas/fishing-areas.component';
import { CustomPipe } from './shared/custom-pipe-example.pipe';
import { RatingComponent } from './shared/rating.component';
import { FishingAreaDetailsComponent } from './fishing-area-details/fishing-area-details.component';
import { LoginComponent } from './login/login.component';
import { FishingAreaGuardService } from './guards/fishing-area-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    FishingAreasListComponent,
    CustomPipe,
    RatingComponent,
    FishingAreaDetailsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: "fishing-areas", component: FishingAreasListComponent },
      { path: "fishing-areas/:id", 
        canActivate: [ FishingAreaGuardService ], 
        component: FishingAreaDetailsComponent },
      { path: "", component: LoginComponent },
      { path: "**", redirectTo: "fishing-areas", pathMatch: 'full' }
    ])
  ],
  providers: [FishingAreaGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
