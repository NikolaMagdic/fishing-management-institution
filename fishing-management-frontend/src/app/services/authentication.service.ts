import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationRequest } from '../models/registration-request';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private url = "http://localhost:8080/api/fisherman";
  
  constructor(private http: HttpClient) { }

  registerFisherman(registration: RegistrationRequest) {
    return this.http.post(this.url, registration);
  }
}
