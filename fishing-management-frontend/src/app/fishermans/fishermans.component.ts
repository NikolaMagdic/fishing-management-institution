import { HtmlTagDefinition } from '@angular/compiler';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { Fisherman } from '../models/fisherman';
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
  filterCategory: string = "Sve kategorije";
  totalPages: any;
  pages: Array<any> = [];

  searchFirstName: string = "";
  searchLastName: string = "";
  searchCategory: number = -1;

  // Za paginaciju da znamo koja lista ribolovaca je trenutno prikazana
  // 0 - Svi
  // 1 - Rezultat pretrage
  // 2 - Ribolovci sa ulovima
  shownFishermans: number = 0;
  currentPage: number = 1;

  constructor(
    private fishermanService: FishermanService,
    private licenseService: LicenseService,
    private router: Router
    ) { }

  ngOnInit() {
    // this.fishermanService.getAllFishermans().subscribe({
    //   next: data => {
    //     this.fishermans = data as Fisherman[];
    //     this.filteredFishermans = this.fishermans;
    //   }
    // });
    this.getFishermansPageable(0);
  }

  getFishermansPageable(page: number) {
    this.fishermanService.getFishermansPageable(page).subscribe({
      next: data => {
        var d = data as any;
        this.fishermans = (d.fishermans) as Fisherman[];
        this.filteredFishermans = this.fishermans;
        this.totalPages = (d.totalPages);
        this.pages = Array.from({length: this.totalPages}, (v, k)=>k+1);
        this.shownFishermans = 0;
      }
    });
  }

  fishermanDetails(fishermanId: number) {
    this.router.navigate(["/fisherman/" + fishermanId]);
  }

  catchesOfFisherman(fishermanId: number) {
    this.router.navigate(["/fisherman/" + fishermanId + "/catches"]);
  }

  getFishermansWithNonConfirmedCatches(page: number) {
    this.fishermanService.getFishermansWithNonConfirmedCatches(page).subscribe({
      next: data => {
        var d = data as any;
        this.fishermans = (d.fishermans) as Fisherman[];
        this.filteredFishermans = this.fishermans;
        this.totalPages = (d.totalPages);
        this.pages = Array.from({length: this.totalPages}, (v, k)=>k+1);
        this.shownFishermans = 2;
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

  searchFishermans(page: number) {
    this.fishermanService.searchFishermansByFirstNameAndLastName(page ,this.searchFirstName, this.searchLastName, this.searchCategory).subscribe({
      next: data => {
        var d = data as any;
        this.fishermans = (d.fishermans) as Fisherman[];
        this.filteredFishermans = this.fishermans;
        this.totalPages = (d.totalPages);
        this.pages = Array.from({length: this.totalPages}, (v, k)=>k+1);
        this.shownFishermans = 1;
      }
    });  
  }

  changePage(page: number) {
    if(this.shownFishermans == 0) {
      this.getFishermansPageable(page)
    } else if(this.shownFishermans == 1) {
      this.searchFishermans(page);
    } else {
      this.getFishermansWithNonConfirmedCatches(page);
    }

    this.currentPage = page + 1;


  }
}
