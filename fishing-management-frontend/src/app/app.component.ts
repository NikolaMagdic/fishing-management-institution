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
  isLoggedIn = this.authService.isLoggedIn();
  role = localStorage.getItem('role');
  correspondingTableId: any = localStorage.getItem('correspondingTableId');

  constructor(private authService: AuthenticationService,
              private router: Router) { }


  ngOnInit() {
    // za promenu menija
    this.authService.loginEvent.subscribe(data => {
      this.isLoggedIn = this.authService.isLoggedIn();
      this.role = data;
      this.correspondingTableId = localStorage.getItem('correspondingTableId');
    });

    this.authService.logoutEvent.subscribe(data => {
      this.isLoggedIn = this.authService.isLoggedIn();
      this.role = "";
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(["/"]);
  }

  
}
