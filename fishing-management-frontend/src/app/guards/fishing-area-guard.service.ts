import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FishingAreaGuardService {

  // Guardovi onemogucavaju pristup ruti za neke korisnike (npr u zavisnosti od uloge) ili  
  // u zavisnosti od nekog uslova (kao u ovom slucaju ako id nije odgovarajuceg formata)
  constructor(private router: Router) { }

  // Pored canActivate postoje i metode canDeactivate(da se ne moze otici sa stranice ako nije ispunjen neki uslov, npr save izmena),
  // Resolve(), CanLoad(da se ucitaju prvo neophodni podaci) itd.
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) : boolean {
      let id = Number(route.paramMap.get("id"));
      if (isNaN(id) || id < 1) {
        alert("Nepravilan id ribolovne vode!");
        this.router.navigate(['/fishing-areas']);
        return false;
      }
      return true;
  }
}
