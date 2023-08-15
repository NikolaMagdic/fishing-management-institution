import { Component, OnInit } from '@angular/core';
import { toStringHDMS } from 'ol/coordinate';
import { Catch } from '../models/catch';
import { CatchItem } from '../models/catch-item';
import { FishSpecies } from '../models/fish-species';
import { FishingArea } from '../models/fishing-area';
import { CatchService } from '../services/catch.service';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishingAreaService } from '../services/fishing-area.service';

@Component({
  selector: 'app-catch-form',
  templateUrl: './catch-form.component.html',
  styleUrls: ['./catch-form.component.css']
})
export class CatchFormComponent {

  fishSpecies: FishSpecies[] = [];
  selectedFish: any; 
  fishingAreas: FishingArea[] = [];
  selectedArea: any;
  catchItems: any = [];
  newItem: CatchItem = new CatchItem(0, 0, 0);
  catch: Catch = new Catch(0, [], new Date());

  // Da ne mogu da se dodaju stavke dok se ne izabere ribolovna voda (jer se na osnovu vode traze ponudjene vrste riba)
  addItemDisabled: boolean = true;
  
  constructor(private catchService: CatchService,
              private fishSpeciesService: FishSpeciesService,
              private fishingAreaService: FishingAreaService) {}
              

  ngOnInit() {
    this.fishingAreaService.getFishingAreas().subscribe({
      next: data => {
        this.fishingAreas = data as FishingArea[];
      }
    }); 
  }

  getFishSpeciesInArea() {
    this.addItemDisabled = false;
    this.fishSpeciesService.getFishSpeciesInArea(this.selectedArea.id).subscribe({
      next: data => {
        this.fishSpecies = data as FishSpecies[];
      }
    });
  }

  addCatchItem() {
    this.newItem.fishId = this.selectedFish.id;
    // Mora ovako jer ako se doda samo newItem to je samo referenca i svi objekti u nizu ce imati trenutnu vrednost newItem, bice isti
    // Na ovaj nacin pravim novi objekat pre dodavanja u niz
    this.catchItems.push(JSON.parse(JSON.stringify(this.newItem)));
    this.catch.catchItems.push(JSON.parse(JSON.stringify(this.newItem)));
  }

  createCatch() {
    this.catch.fishingAreaId = this.selectedArea.id;
    this.catchService.createCatch(this.catch).subscribe({
    });
  }

  findFishNameById(fishId: number) {
    var filteredFish = this.fishSpecies.filter(fish => fish.id == fishId);
    return filteredFish[0].name;
  }

}
