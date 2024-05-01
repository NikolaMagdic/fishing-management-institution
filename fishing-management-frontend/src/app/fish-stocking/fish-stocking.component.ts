import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FishSpecies } from '../models/fish-species';
import { FishingArea } from '../models/fishing-area';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishStockingService } from '../services/fish-stocking.service';
import { FishingAreaService } from '../services/fishing-area.service';

@Component({
  selector: 'app-fish-stocking',
  templateUrl: './fish-stocking.component.html',
  styleUrls: ['./fish-stocking.component.css']
})
export class FishStockingComponent {

  modifications: any = [];
  fishingAreas: FishingArea[] = [];
  fishSpecies: FishSpecies[] = [];
  nativeFishSpecies: FishSpecies[] = [];
  fishStockingForm: FormGroup;
  addFishStockingButtonVisible = false;
  
  filterValue = "Sve";
  filteredModifications: any = [];

  typeIsFishStocking = true;

  constructor(
    private fishStockingService: FishStockingService,
    private fishingAreaService: FishingAreaService,
    private fishSpeciesService: FishSpeciesService
    ) 
    {
      this.fishStockingForm = new FormGroup({
        fishingArea: new FormControl(),
        fishSpecies: new FormControl(),
        modificationType: new FormControl(),
        date: new FormControl(),
        totalWeight: new FormControl(),
        amount: new FormControl()
      });
    }

  ngOnInit() {

    this.getAllFishStockings();
    this.getAllFishingAreas();
    this.getAllFishSpecies();
    this.getNativeFishSpecies();

    const role = localStorage.getItem('role');
    if(role == "ROLE_KEEPER") {
      this.addFishStockingButtonVisible = true;
    }
  }

  getAllFishStockings() {
    this.fishStockingService.getAllFishStockings().subscribe({
      next: data => {
        this.modifications = data;
        this.filteredModifications = this.modifications;
      }
    });
  }

  getAllFishingAreas() {
    this.fishingAreaService.getFishingAreas().subscribe({
      next: data => {
        this.fishingAreas = data as FishingArea[];
      }
    });
  }

  getAllFishSpecies() {
    this.fishSpeciesService.getFishSpecies().subscribe({
      next: data => {
        this.fishSpecies = data as FishSpecies[];
      }
    });
  }

  getNativeFishSpecies() {
    this.fishSpeciesService.getNativeFishSpecies().subscribe({
      next: data => {
        this.nativeFishSpecies = data as FishSpecies[];
      }
    });
  }

  makeFishStocking() {
    var newFishStocking = {
      "fishingAreaId": this.fishStockingForm.value.fishingArea.id,
      "fishSpeciesId": this.fishStockingForm.value.fishSpecies.id,
      "date": this.fishStockingForm.value.date,
      "modificationType": this.fishStockingForm.value.modificationType,
      "totalWeight": this.fishStockingForm.value.totalWeight,
      "amount": this.fishStockingForm.value.amount
    }

    this.fishStockingService.makeFishStocking(newFishStocking).subscribe({
      next: () => {
        this.getAllFishStockings();
        this.fishStockingForm.reset();
      }
    });
  }

  filterModifications() {
    this.filteredModifications = this.modifications.filter(
      (modification: {modificationType: string}) =>
      (modification?.modificationType === this.filterValue || this.filterValue === "Sve")
    );
  }

  changeModificationType() {
    if(this.fishStockingForm.value.modificationType === "FISH_STOCKING") {
      this.typeIsFishStocking = true;
    } else {
      this.typeIsFishStocking = false;
    }
  }
}
