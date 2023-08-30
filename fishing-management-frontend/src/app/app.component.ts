import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { FishingAreaService } from './services/fishing-area.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [FishingAreaService]
})
export class AppComponent {
  title = 'fishing-management-frontend';

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(["/"]);
  }
}
