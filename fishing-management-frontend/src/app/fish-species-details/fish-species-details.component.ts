import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FishSpecies } from '../models/fish-species';
import { FishSpeciesService } from '../services/fish-species.service';

@Component({
  selector: 'app-fish-species-details',
  templateUrl: './fish-species-details.component.html',
  styleUrls: ['./fish-species-details.component.css']
})
export class FishSpeciesDetailsComponent {

  fishSpecies: FishSpecies | any;

  // Ovo moze da se uradi na dva nacina: preko property bindinga (kao u submitButton) 
  // ili preko modifikovanja DOM stabla dobavljanjem elementa putem id-ja pomocu @ViewChild, ovde su prikazana oba nacina
  @ViewChild("fieldset") fieldset: ElementRef | any;
  submitButtonHidden: boolean = true;
  @ViewChild("changeButton") changeButton: ElementRef | any;

  constructor(private route: ActivatedRoute,
              private fishSpeciesService: FishSpeciesService) {}

  ngOnInit() {
    let id = Number(this.route.snapshot.paramMap.get('id'));

    this.fishSpeciesService.getFishSpeciesById(id).subscribe({
      next: data => {
        this.fishSpecies = data;
      }
    })
  }

  enableForm() {
    this.fieldset.nativeElement.removeAttribute('disabled');
    this.submitButtonHidden = false;
    this.changeButton.nativeElement.setAttribute('hidden', true);
  }

  updateFishSpecies() {
    this.fishSpeciesService.updateFishSpecies(this.fishSpecies).subscribe({
      next: () => {
        this.fieldset.nativeElement.setAttribute('disabled', true);
        this.submitButtonHidden = true;
        this.changeButton.nativeElement.removeAttribute('hidden');
      }
    });
  }
}
