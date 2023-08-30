import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { AuthenticationRequest } from '../models/authentication-request';
import { AuthenticationResponse } from '../models/authentication-response';
import { RegistrationRequest } from '../models/registration-request';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private url = "http://localhost:8080/api/auth";
  private registerUrl = "http://localhost:8080/api/fisherman";

  private authResponse: AuthenticationResponse | any;

  constructor(private http: HttpClient,
              private router: Router) { }

  registerFisherman(registration: RegistrationRequest) {
    return this.http.post(this.registerUrl, registration);
  }

  login(authRequest: AuthenticationRequest) {
    return this.http.post(this.url + "/login", authRequest)
      .pipe(
        tap(res => {
          this.authResponse = res as AuthenticationResponse;
          localStorage.setItem('token', this.authResponse.jwtToken);
          localStorage.setItem('expiresIn', this.authResponse.expiresIn);
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('expiresIn');
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return localStorage.getItem('token') != null;
  }

  changePassword(passwordChangeRequest: any) {
    return this.http.put(this.url, passwordChangeRequest);
  }
}
