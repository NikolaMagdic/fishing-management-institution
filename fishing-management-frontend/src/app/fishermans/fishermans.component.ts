import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Fisherman } from '../models/fisherman';
import { CatchService } from '../services/catch.service';
import { FishermanService } from '../services/fisherman.service';
import { LicenseService } from '../services/license.service';

@Component({
  selector: 'app-fishermans',
  templateUrl: './fishermans.component.html',
  styleUrls: ['./fishermans.component.css']
})
export class FishermansComponent {

  fishermans: Fisherman[] = [];
  filteredFishermans: Fisherman[] = [];
  filterFirstName: string = "";
  filterLastName: string = "";
  filterCategory: string = "Sve kategorije"

  constructor(
    private fishermanService: FishermanService,
    private licenseService: LicenseService,
    private router: Router
    ) {}

  ngOnInit() {
    this.fishermanService.getAllFishermans().subscribe({
      next: data => {
        this.fishermans = data as Fisherman[];
        this.filteredFishermans = this.fishermans;
      }
    });
  }

  fishermanDetails(fishermanId: number) {
    this.router.navigate(["/fisherman/" + fishermanId]);
  }

  catchesOfFisherman(fishermanId: number) {
    this.router.navigate(["/fisherman/" + fishermanId + "/catches"]);
  }

  getFishermansWithNonConfirmedCatches() {
    this.fishermanService.getFishermansWithNonConfirmedCatches().subscribe({
      next: data => {
        this.filteredFishermans = data as Fisherman[];
      }
    });
  }

  filterFishermans() {
    if(!this.filterFirstName && !this.filterLastName && this.filterCategory === "Sve kategorije") {
      this.filteredFishermans = this.fishermans;
    }
    
    this.filteredFishermans = this.fishermans.filter(
      fisherman => ((fisherman?.firstName.toLowerCase())
                  .includes(this.filterFirstName.toLowerCase()) &&
                  (fisherman?.lastName.toLowerCase())
                  .includes(this.filterLastName.toLowerCase()) &&
                  (fisherman?.category === this.filterCategory || this.filterCategory === "Sve kategorije"))
    );
  }

  checkFishermanLicenses(fishermanId: number) {
    this.licenseService.checkValidLicenseOfFishermanForToday(fishermanId).subscribe({
      next: data => {
        let f: Fisherman[] = this.filteredFishermans.filter(fisherman => (fisherman?.id === fishermanId));
        f[0].license = data as boolean;
      }
    }); 
  }

}
