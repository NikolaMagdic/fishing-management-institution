import { formatDate } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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

  fishForm: FormGroup;

  updateButtonVisible = false;

  constructor(private route: ActivatedRoute,
              private fishSpeciesService: FishSpeciesService) {
      this.fishForm = new FormGroup({
        name: new FormControl(),
        latinName: new FormControl(),
        category: new FormControl(),
        minSize: new FormControl(),
        maxQuantity: new FormControl(),
        fishingBanStartDay: new FormControl(),
        fishingBanStartMonth: new FormControl(),
        fishingBanEndDay: new FormControl(),
        fishingBanEndMonth: new FormControl(),
        permanentFishingBan: new FormControl()
      }); 
  }

  ngOnInit() {
    let id = Number(this.route.snapshot.paramMap.get('id'));

    this.fishSpeciesService.getFishSpeciesById(id).subscribe({
      next: data => {
        this.fishSpecies = data as FishSpecies;
        console.log(this.fishSpecies);
        this.fishForm.setValue({
          name: this.fishSpecies.name,
          latinName: this.fishSpecies.latinName,
          category: this.fishSpecies.category,
          minSize: this.fishSpecies.minSize,
          maxQuantity: this.fishSpecies.maxQuantity,
          fishingBanStartDay: this.fishSpecies.fishingBanStartDay,
          fishingBanStartMonth: this.fishSpecies.fishingBanStartMonth,
          fishingBanEndDay: this.fishSpecies.fishingBanEndDay,
          fishingBanEndMonth: this.fishSpecies.fishingBanEndMonth,
          permanentFishingBan: this.fishSpecies.permanentFishingBan
        });
      }
    })

    const role = localStorage.getItem('role');
    if(role == "ROLE_ADMIN") {
      this.updateButtonVisible = true;
    }
  }

  enableForm() {
    this.fieldset.nativeElement.removeAttribute('disabled');
    this.submitButtonHidden = false;
    this.changeButton.nativeElement.setAttribute('hidden', true);
  }

  updateFishSpecies() {
    let f = new FishSpecies(this.fishSpecies.id,
        this.fishForm.value.name,
        this.fishForm.value.latinName,
        this.fishForm.value.category,
        this.fishForm.value.minSize,
        this.fishForm.value.maxQuantity,
        this.fishForm.value.fishingBanStartDay,
        this.fishForm.value.fishingBanStartMonth,
        this.fishForm.value.fishingBanEndDay,
        this.fishForm.value.fishingBanEndMonth,
        this.fishForm.value.permanentFishingBan,
        this.fishSpecies.image);

    this.fishSpeciesService.updateFishSpecies(f).subscribe({
      next: () => {
        this.fieldset.nativeElement.setAttribute('disabled', true);
        this.submitButtonHidden = true;
        this.changeButton.nativeElement.removeAttribute('hidden');
      }
    });
  }
}
