import { Component, ElementRef, ViewChild } from '@angular/core';
import { License } from '../models/license';
import { FishermanService } from '../services/fisherman.service';
import { LicenseService } from '../services/license.service';

@Component({
  selector: 'app-license',
  templateUrl: './license.component.html',
  styleUrls: ['./license.component.css']
})
export class LicenseComponent {

  validLicense: boolean = false;
  license: License | any;
  licenseDate: Date | any;
  dailyLicenses: any = [];
  // Visednevne dozvole
  licenseDateStart: Date | any;
  licenseDateEnd: Date | any;
  multiDayLicenses: any = [];
  professionalFishermanLoggedIn: boolean = false;

  modalMessage = "";
  @ViewChild('openModal') openModal: ElementRef | any;

  constructor(private licenseService: LicenseService, private fishermanService: FishermanService) { }

  ngOnInit() {
    this.licenseService.getExistingValidLicenses().subscribe({
      next: data => {
        if(data != null) {
          this.validLicense = true;
          this.license = data;
        } 
      }
    });

    this.fishermanService.getCategoryOfLoggedFisherman().subscribe({
      next: data => {
        let category: string = data as string;
        console.log(category);
        if(category === "Profesionalni") {
          this.professionalFishermanLoggedIn = true;
        } 
      }  
    });

    this.getDailyLicenses();
    this.getMultiDayLicenses();
  }

  obtainLicence() {
    const license: License = new License(0, "YEARLY", new Date(), new Date(), 0);
    this.licenseService.obtainYearLicense(license).subscribe({
      next: () => {
        window.location.reload();
      },
    })
  }

  obtainDailyLicense() {
    const license: License = new License(0, "DAILY", this.licenseDate, new Date(), 0);
    this.licenseService.obtainLicence(license).subscribe({
      next: () => {
        this.modalMessage = "Zahtev za dozvolu uspešno poslat";
        this.openModal.nativeElement.click();
      },
      error: () => {
        this.modalMessage = "Zahtev za dozvolu neispravan";
        this.openModal.nativeElement.click();
      }
    })
  }

  obtainMultiDayLicense() {
    const license: License = new License(0, "MULTIDAY", this.licenseDateStart, this.licenseDateEnd, 0);
    this.licenseService.obtainLicence(license).subscribe({
      next: () => {
        this.modalMessage = "Zahtev za dozvolu uspešno poslat"
        this.openModal.nativeElement.click();
      },
      error: () => {
        this.modalMessage = "Zahtev za dozvolu neispravan";
        this.openModal.nativeElement.click();
      }
    });
  }

  getDailyLicenses() {
    this.licenseService.getDailyLicenses().subscribe({
      next: data => {
        this.dailyLicenses = data;
      }
    });
  }

  getMultiDayLicenses() {
    this.licenseService.getMultyDayLicenses().subscribe({
      next: data => {
        this.multiDayLicenses = data;
        console.log(this.multiDayLicenses);
      }
    });
  }
 
  reloadPage() {
    window.location.reload();
  }
}
