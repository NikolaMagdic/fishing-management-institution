import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationRequest } from '../models/registration-request';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  public registration: RegistrationRequest = new RegistrationRequest("", "", "", "", "", new Date(), "", "", "");
  passwordsMatch: boolean = false;

  @ViewChild('openModal') openModal: ElementRef | any;
  modalText: string = "Zahtev za registraciju poslat. Potvrdite zahtev klikom na link koji Vam je stigao na imejl adresu.";

  constructor(private authenticationService: AuthenticationService,
    private router: Router) {}


  register() {
    this.authenticationService.registerFisherman(this.registration).subscribe({
      next: () => {
        this.openModal.nativeElement.click();
      }
    });
  }

  navigateOutside() {
    this.router.navigate(['']);
  }

  checkIfPasswordsMatch() {
    if(this.registration.password === this.registration.confirmPassword) {
      this.passwordsMatch = true;
    } else {
      this.passwordsMatch = false;
    }
  }
}
