import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  fishermanLoggedIn = false;
  yearCatchesHidden = true;
  // Izbor godine za prikaz godinsnjeg ulova
  year = new Date().getFullYear();

  // Filtracija dnevnog ulova po godinama
  selectedYear = new Date().getFullYear();

  updateItemForm: FormGroup;
  updatedCatchId: number = 0;
  updatedItemId: number = 0;

  @ViewChild('failureModal') failureModal: ElementRef | any;
  @ViewChild('updateItemModal') updateItemModal: ElementRef | any;

  constructor(
    private catchService: CatchService,
    private route: ActivatedRoute) 
    {
      this.updateItemForm = new FormGroup({
        date: new FormControl({disabled: true}),
        fish: new FormControl({disabled: true}),
        quantity: new FormControl(),
        weight: new FormControl()
      });
    }

  ngOnInit() {
    let fishermanId = Number(this.route.snapshot.paramMap.get('id'));

    this.catchService.getAllCatchesOfFisherman(fishermanId).subscribe({
      next: data => {
        this.catches = data;
      }
    });

    const role = localStorage.getItem('role');
    if(role == "ROLE_KEEPER") {
      this.keeperLoggedIn = true;  
    } else if(role == "ROLE_FISHERMAN") {
      this.fishermanLoggedIn = true;
    }
  }

  confirmCatch(catchId: number, itemId: number) {
    this.catchService.confirmCatch(catchId, itemId).subscribe({
      next: () => {
        window.location.reload();
      },
      error: () => {
        this.failureModal.nativeElement.click();
      }
    });
  }

  rejectCatch(catchId: number, itemId: number) {
    this.catchService.rejectCatch(catchId, itemId).subscribe({
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

  openUpdateCatchModal(dailyCatch: any, item: any) {
    this.updatedCatchId = dailyCatch.id;
    this.updatedItemId = item.id;
    
    this.updateItemForm.setValue({
      date: dailyCatch.date,
      fish: item.fishSpeciesName,
      quantity: item.quantity,
      weight: item.weight
    });

    this.updateItemModal.nativeElement.click();
  }

  updateCatchItem() {
     var catchItem = {
      quantity: this.updateItemForm.value.quantity,
      weight: this.updateItemForm.value.weight
     };
     this.catchService.updateCatchItem(catchItem, this.updatedCatchId, this.updatedItemId).subscribe({
      next: () => {
        window.location.reload();
      }
     });
  }
}
