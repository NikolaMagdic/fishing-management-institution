import { Component } from '@angular/core';
import { License } from '../models/license';
import { LicenseService } from '../services/license.service';

@Component({
  selector: 'app-license',
  templateUrl: './license.component.html',
  styleUrls: ['./license.component.css']
})
export class LicenseComponent {

  validLicense: boolean = false;
  license: License | any;
  licenseDate: Date = new Date();

  constructor(private licenseService: LicenseService) { }

  ngOnInit() {
    this.licenseService.getExistingValidLicenses().subscribe({
      next: data => {
        if(data != null) {
          this.validLicense = true;
          this.license = data;
        } 
      }
    });
  }

  obtainLicence() {
    const license: License = new License(0, "YEARLY", new Date(), 0)
    this.licenseService.obtainLicence(license).subscribe({
      next: () => {
        window.location.reload();
      }
    })
  }

  obtainDailyLicense() {
    const license: License = new License(0, "DAILY", this.licenseDate, 0)
    this.licenseService.obtainLicence(license).subscribe({
      next: () => {
        window.location.reload();
      }
    })
  }

}
