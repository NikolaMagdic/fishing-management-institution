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

  public registration: RegistrationRequest = new RegistrationRequest("", "", "", "", "", new Date(), "", "", "", null);
  emailValid: boolean = false;
  passwordsMatch: boolean = false;
  passwordLengthOk: boolean = false;

  @ViewChild('openModal') openModal: ElementRef | any;
  modalText: string = "Zahtev za registraciju poslat. Potvrdite zahtev klikom na link koji Vam je stigao na imejl adresu.";

  professionalFishermanSelected: boolean = false;

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

  // Poredjenje stringa sa regularnim izrazom
  checkIfEmailIsValid() {
    if(/^\S+@\S+\.\S+$/.test(this.registration.username)) {
      this.emailValid = true;
    } else {
      this.emailValid = false;
    }
  }

  checkIfPasswordsMatch() {
    if(this.registration.password === this.registration.confirmPassword) {
      this.passwordsMatch = true;
    } else {
      this.passwordsMatch = false;
    }

    if(this.registration.password.length < 8) {
      this.passwordLengthOk = false;
    } else {
      this.passwordLengthOk = true;
    }
  }

  addFormField() {
    if(this.registration.category === "PROFESSIONAL") {
      this.professionalFishermanSelected = true;
    } else {
      this.professionalFishermanSelected = false;
    }
  }
}
