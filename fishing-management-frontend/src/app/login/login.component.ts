import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
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
  invalidCredentials = false;

  constructor(private authService: AuthenticationService, 
              private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    })
  }

  login() {
    this.authRequest = new AuthenticationRequest(this.loginForm.value.username, this.loginForm.value.password);
    this.authService.login(this.authRequest).subscribe({
      next: res => {
        let role = localStorage.getItem('role');
        if(role === "ROLE_ADMIN") {
          this.router.navigate(['/fishing-areas']);
        } else if(role === "ROLE_FISHERMAN") {
          this.router.navigate(['/fishing-areas']);
        } else if(role == "ROLE_KEEPER") {
          this.router.navigate(['/fishing-areas']);
        }   
      },
      error: err => {
        this.invalidCredentials = true;
      }
    });
  }
}
