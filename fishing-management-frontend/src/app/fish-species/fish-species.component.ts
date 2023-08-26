import { Component, ɵdevModeEqual } from '@angular/core';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishSpecies } from '../models/fish-species';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-fish-species',
  templateUrl: './fish-species.component.html',
  styleUrls: ['./fish-species.component.css']
})
export class FishSpeciesComponent {
  
  fishSpecies: any = [];
  newFishForm: FormGroup;

  constructor(private fishSpeciesService: FishSpeciesService,
              private router: Router) {
                this.newFishForm = new FormGroup({
                  name: new FormControl(),
                  latinName: new FormControl(),
                  category: new FormControl(),
                  minSize: new FormControl(),
                  maxQuantity: new FormControl(),
                  maxWeight: new FormControl(),
                  fishingBanStart: new FormControl(),
                  fishingBanEnd: new FormControl(),
                  permanentFishingBan: new FormControl(),
                  image: new FormControl()
                });
              }

  createFishSpecies() {
    var newFish = new FishSpecies(
      0, this.newFishForm.value.name, 
      this.newFishForm.value.latinName,
      this.newFishForm.value.category,
      this.newFishForm.value.minSize,
      this.newFishForm.value.maxQuantity,
      this.newFishForm.value.maxWeight,
      this.newFishForm.value.fishingBanStart,
      this.newFishForm.value.fishingBanEnd,
      this.newFishForm.value.permanentFishingBan,
      this.newFishForm.value.image
    );
    this.fishSpeciesService.createFishSpecies(newFish).subscribe({
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
