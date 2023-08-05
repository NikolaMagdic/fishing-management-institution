import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FishSpecies } from '../models/fish-species';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishingAreaService } from '../services/fishing-area.service';

@Component({
  selector: 'app-fishing-area-details',
  templateUrl: './fishing-area-details.component.html',
  styleUrls: ['./fishing-area-details.component.css']
})
export class FishingAreaDetailsComponent implements OnInit{
  pageTitle: string = 'Ribolovna voda';
  fishingArea: any | undefined;
  fishSpeciesInArea: FishSpecies[] | any = [];
  fishSpeciesNotInArea: FishSpecies[] | any = [];
  selectedFish: FishSpecies | any;

  constructor(private _route: ActivatedRoute, 
              private _fishingAreaService: FishingAreaService,
              private _router: Router,
              private fishSpeciesService: FishSpeciesService) { }

  ngOnInit() {
    // Uz pomoc ActivatedRoute izvlacimo vrednost id-ja iz route
    // Moramo castovati u Number jer je parametar tipa String
    let id = Number(this._route.snapshot.paramMap.get('id'));
    this._fishingAreaService.getFishingAreaById(id).subscribe({
      next: fishingArea => {
        this.fishingArea = fishingArea;
      }
    });
    
    this.fishSpeciesService.getFishSpeciesInArea(id).subscribe({
      next: data => {
        this.fishSpeciesInArea = data;
      }
    });

    this.fishSpeciesService.getFishSpeciesOutsideArea(id).subscribe({
      next: data => {
        this.fishSpeciesNotInArea = data;
      }
    });
  }

  // Primer rutiranja iz koda (bez koriscenja routerLink-a) ovo se najcesce
  // koristi kada se radi Save necega na dugme pa zatim rutira
  onBack() : void {
    this._router.navigate(['/fishing-areas']);  
  }

  addFishSpecies() {
    console.log(this.selectedFish);
    this._fishingAreaService.addFishSpeciesToArea(this.fishingArea.id, this.selectedFish.id).subscribe({
      next: () => {
        window.location.reload();
        // Moram ovo opet pozvati da se osveze liste
        let id = Number(this._route.snapshot.paramMap.get('id'));
        this.fishSpeciesService.getFishSpeciesInArea(id).subscribe({
          next: data => {
            this.fishSpeciesInArea = data;
          }
        });
        this.fishSpeciesService.getFishSpeciesOutsideArea(id).subscribe({
          next: data => {
            this.fishSpeciesNotInArea = data;
          }
        });
    
      }
    });
  }
}
