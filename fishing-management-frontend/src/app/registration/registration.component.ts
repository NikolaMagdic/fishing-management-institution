import { Component } from '@angular/core';
import { RegistrationRequest } from '../models/registration-request';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  public registration: RegistrationRequest = new RegistrationRequest("", "", "", "", new Date(), "", "", "");

  constructor(private authenticationService: AuthenticationService) {}

  register() {
    this.authenticationService.registerFisherman(this.registration).subscribe({

    });
  }
}
