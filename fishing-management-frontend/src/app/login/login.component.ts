import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationRequest } from '../models/authentication-request';
import { AuthenticationResponse } from '../models/authentication-response';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;
  authRequest: AuthenticationRequest | any;
  authResponse: AuthenticationResponse | any;

  constructor(private authService: AuthenticationService) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    })
  }

  login() {
    this.authRequest = new AuthenticationRequest(this.loginForm.value.username, this.loginForm.value.password);
    this.authService.login(this.authRequest).subscribe({
      next: res => {
        

      }
    })
  }
}
