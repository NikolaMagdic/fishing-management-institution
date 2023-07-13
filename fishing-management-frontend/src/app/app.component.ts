import { Component } from '@angular/core';
import { FishingAreaService } from './services/fishing-area.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [FishingAreaService]
})
export class AppComponent {
  title = 'fishing-management-frontend';
}
