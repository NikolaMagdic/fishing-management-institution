import { Component } from '@angular/core';
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

  constructor(private authService: AuthenticationService) {}

  changePassword() {
    this.authService.changePassword(this.passwordChangeRequest).subscribe({});
  }
}
