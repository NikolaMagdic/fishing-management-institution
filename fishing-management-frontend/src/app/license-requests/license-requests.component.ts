import { Component } from '@angular/core';
import { Fisherman } from '../models/fisherman';
import { FishermanService } from '../services/fisherman.service';
import { LicenseService } from '../services/license.service';

@Component({
  selector: 'app-license-requests',
  templateUrl: './license-requests.component.html',
  styleUrls: ['./license-requests.component.css']
})
export class LicenseRequestsComponent {

  fishermans: Fisherman[] = [];

  constructor(private fishermanService: FishermanService,
              private licenseService: LicenseService) {}

  ngOnInit() {
    this.fishermanService.getFishermansWithLicenseRequest().subscribe({
      next: data => {
        this.fishermans = data as Fisherman[];
      }
    });
  }

  acceptLicenseRequest(fishermanId: number) {
    this.licenseService.confirmLicenseRequest(fishermanId).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }
}
