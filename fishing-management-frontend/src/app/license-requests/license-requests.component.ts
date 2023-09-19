import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Fisherman } from '../models/fisherman';
import { FishermanService } from '../services/fisherman.service';
import { LicenseService } from '../services/license.service';

@Component({
  selector: 'app-license-requests',
  templateUrl: './license-requests.component.html',
  styleUrls: ['./license-requests.component.css']
})
export class LicenseRequestsComponent {

  licenseRequests: any = [];

  constructor(private fishermanService: FishermanService,
              private licenseService: LicenseService,
              private router: Router) {}

  ngOnInit() {
    this.licenseService.getAllLicenseRequests().subscribe({
      next: data => {
        this.licenseRequests = data; 
      }
    });
  }

  acceptLicenseRequest(licenseId: number) {
    this.licenseService.confirmLicenseRequest(licenseId).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }

  rejectLicenseRequest(licenseId: number) {
    this.licenseService.rejectLicenseRequest(licenseId).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }

  showFishermanDetails(fishermanId: number) {
    this.router.navigate(["/fisherman/" + fishermanId]);
  }
}
