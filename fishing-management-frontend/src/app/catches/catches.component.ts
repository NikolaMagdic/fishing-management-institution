import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Catch } from '../models/catch';
import { CatchService } from '../services/catch.service';

@Component({
  selector: 'app-catches',
  templateUrl: './catches.component.html',
  styleUrls: ['./catches.component.css']
})
export class CatchesComponent {

  catches: any = [];
  yearCatches: any = [];
  keeperLoggedIn = false;
  yearCatchesHidden = true;
  // Izbor godine za prikaz godinsnjeg ulova
  year = new Date().getFullYear();

  // Filtracija dnevnog ulova po godinama
  selectedYear = new Date().getFullYear();

  @ViewChild('failureModal') failureModal: ElementRef | any;

  constructor(
    private catchService: CatchService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    let fishermanId = Number(this.route.snapshot.paramMap.get('id'));

    this.catchService.getAllCatchesOfFisherman(fishermanId).subscribe({
      next: data => {
        this.catches = data;
        console.log(this.catches);
      }
    });

    const role = localStorage.getItem('role');
    if(role == "ROLE_KEEPER") {
      this.keeperLoggedIn = true;  
    }
  }

  confirmCatch(itemId: number) {
    this.catchService.confirmCatch(itemId).subscribe({
      next: () => {
        window.location.reload();
      },
      error: () => {
        this.failureModal.nativeElement.click();
      }
    });
  }

  rejectCatch(itemId: number) {
    this.catchService.rejectCatch(itemId).subscribe({
      next: () => {
        window.location.reload();
      },
      error: () => {
        this.failureModal.nativeElement.click();
      }
    });
  }

  showYearlyCatch() {
    let fishermanId = Number(this.route.snapshot.paramMap.get('id'));

    this.catchService.getYearlyCatchesOfFisherman(fishermanId, this.year).subscribe({
      next: data => {
        this.yearCatches = data;
        this.yearCatchesHidden = false;
      }
    });
  }

  filterCatches() {
    let fishermanId = Number(this.route.snapshot.paramMap.get('id'));

    this.catchService.getAllCatchesOfFishermanForYear(fishermanId, this.selectedYear).subscribe({
      next: data => {
        this.catches = data;
      }
    });
  }
}
