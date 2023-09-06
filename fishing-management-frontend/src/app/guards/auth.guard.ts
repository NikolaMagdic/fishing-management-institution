import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {

  constructor() {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let role =  localStorage.getItem('role');
    if(role) {
      const { roles } = route.data;
      if(roles && !roles.includes(role)) {
        // Ovde bi mogla neka greska da se ispise korisniku
        return false;
      }
      return true;
    }

    return false;
  }

}
