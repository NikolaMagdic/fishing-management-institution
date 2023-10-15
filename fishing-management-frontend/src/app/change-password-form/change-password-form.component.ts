import { Component, ElementRef, ViewChild } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-change-password-form',
  templateUrl: './change-password-form.component.html',
  styleUrls: ['./change-password-form.component.css']
})
export class ChangePasswordFormComponent {

  passwordChangeRequest = {
    oldPassword: "",
    newPassword: ""
  }
  confirmPassword: string = "";
  passwordsMatch: boolean = false;
  @ViewChild('openModal') openModal: ElementRef | any;
  modalText = "";

  constructor(private authService: AuthenticationService) {}

  changePassword() {
    this.authService.changePassword(this.passwordChangeRequest).subscribe({
      next: () => {
        this.modalText = "Lozinka uspešno izmenjena";
        this.openModal.nativeElement.click();
      },
      error: () => {
        this.modalText = "Niste uneli ispravnu lozinku";
        this.openModal.nativeElement.click();
      }
    });
  }

  checkIfPasswordsMatch() {
   
    if(this.passwordChangeRequest.newPassword == this.confirmPassword) {
      this.passwordsMatch = true;
    } else {
      this.passwordsMatch = false;
    }
  }

  reloadPage() {
    window.location.reload();
  }
}
