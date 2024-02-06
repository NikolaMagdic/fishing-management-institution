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

  stockings: any = [];
  fishingAreas: FishingArea[] = [];
  fishSpecies: FishSpecies[] = [];
  fishStockingForm: FormGroup;
  addFishStockingButtonVisible = false;

  constructor(
    private fishStockingService: FishStockingService,
    private fishingAreaService: FishingAreaService,
    private fishSpeciesService: FishSpeciesService
    ) 
    {
      this.fishStockingForm = new FormGroup({
        fishingArea: new FormControl(),
        fishSpecies: new FormControl(),
        date: new FormControl(),
        number: new FormControl()
      });
    }

  ngOnInit() {

    this.getAllFishStockings();
    this.getAllFishingAreas();
    this.getAllFishSpecies();

    const role = localStorage.getItem('role');
    if(role == "ROLE_KEEPER") {
      this.addFishStockingButtonVisible = true;
    }
  }

  getAllFishStockings() {
    this.fishStockingService.getAllFishStockings().subscribe({
      next: data => {
        this.stockings = data;
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
    this.fishSpeciesService.getNativeFishSpecies().subscribe({
      next: data => {
        this.fishSpecies = data as FishSpecies[];
      }
    });
  }

  makeFishStocking() {
    var newFishStocking = {
      "fishingAreaId": this.fishStockingForm.value.fishingArea.id,
      "fishSpeciesId": this.fishStockingForm.value.fishSpecies.id,
      "date": this.fishStockingForm.value.date,
      "number": this.fishStockingForm.value.number
    }

    this.fishStockingService.makeFishStocking(newFishStocking).subscribe({
      next: () => {
        this.getAllFishStockings();
        this.fishStockingForm.reset();
      }
    });
  }
}
