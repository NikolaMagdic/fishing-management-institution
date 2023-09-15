import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
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

  @Output() loginEvent = new EventEmitter<string>();
  @Output() logoutEvent = new EventEmitter<boolean>();

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
          localStorage.setItem('role', this.authResponse.role);
          localStorage.setItem('correspondingTableId', this.authResponse.correspondingTableId);

          // Objavljujem koju rolu ima prijavljeni korisnik kako bih to mogao uhvatiti u app.component
          // i shodno tome izmeniti moguce opcije u meniju
          this.loginEvent.emit(this.authResponse.role);
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('expiresIn');
    localStorage.removeItem('role');
    localStorage.removeItem('correspondingTableId');

    this.logoutEvent.emit(true);
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return localStorage.getItem('token') != null;
  }

  changePassword(passwordChangeRequest: any) {
    return this.http.put(this.url, passwordChangeRequest);
  }
}
