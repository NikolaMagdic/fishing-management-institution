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

  public registration: RegistrationRequest = new RegistrationRequest("", "", "", "", new Date(), "", "", "");
  
  @ViewChild('openModal') openModal: ElementRef | any;
  modalText: string = "Registracija uspešno izvršena";

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
    this.router.navigate(['/fishing-areas']);
  }
}
