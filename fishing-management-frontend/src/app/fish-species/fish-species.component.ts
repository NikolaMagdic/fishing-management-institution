import { Component, ɵdevModeEqual } from '@angular/core';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishSpecies } from '../models/fish-species';
import { Router } from '@angular/router';
declare var bootstrap: any;

@Component({
  selector: 'app-fish-species',
  templateUrl: './fish-species.component.html',
  styleUrls: ['./fish-species.component.css']
})
export class FishSpeciesComponent {
  
  fishSpecies: any = [];
  newFish: FishSpecies = new FishSpecies(0, "", "", "", 0, 0, 0, new Date(), new Date(), false, "");

  constructor(private fishSpeciesService: FishSpeciesService,
              private router: Router) {}

  createFishSpecies() {
    this.fishSpeciesService.createFishSpecies(this.newFish).subscribe({
      next: () => {
        window.location.reload();
      }
    });  

  }

  ngOnInit() : void {
    this.fishSpeciesService.getFishSpecies().subscribe({
      next: data => {
        this.fishSpecies = data;
      }
    })
  }

  viewFishSpeciesDetails(id: number) {
    this.router.navigate(['/fish-species-details/' + id]);
  }
}
