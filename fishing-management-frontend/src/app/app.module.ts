import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FishingAreasListComponent } from './fishing-areas/fishing-areas.component';
import { CustomPipe } from './shared/custom-pipe-example.pipe';
import { RatingComponent } from './shared/rating.component';

@NgModule({
  declarations: [
    AppComponent,
    FishingAreasListComponent,
    CustomPipe,
    RatingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
