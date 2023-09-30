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
  addFishButtonVisible = false;
  imposibleDate = false;
  file: any;

  constructor(private fishSpeciesService: FishSpeciesService,
              private router: Router) {
                this.newFishForm = new FormGroup({
                  name: new FormControl(),
                  latinName: new FormControl(),
                  category: new FormControl(),
                  minSize: new FormControl(),
                  maxQuantity: new FormControl(),
                  fishingBanStartDay: new FormControl(),
                  fishingBanStartMonth: new FormControl(),
                  fishingBanEndDay: new FormControl(),
                  fishingBanEndMonth: new FormControl(),
                  permanentFishingBan: new FormControl(),
                  image: new FormControl()
                });
              }

  createFishSpecies() {
    // let reader = new FileReader();
    // let a  = reader.readAsDataURL(this.newFishForm.value.image);
    // console.log(a);

    var newFish = new FishSpecies(
      0, this.newFishForm.value.name, 
      this.newFishForm.value.latinName,
      this.newFishForm.value.category,
      this.newFishForm.value.minSize,
      this.newFishForm.value.maxQuantity,
      this.newFishForm.value.fishingBanStartDay,
      this.newFishForm.value.fishingBanStartMonth,
      this.newFishForm.value.fishingBanEndDay,
      this.newFishForm.value.fishingBanEndMonth,
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

    const role = localStorage.getItem('role');
    if(role == "ROLE_ADMIN") {
      this.addFishButtonVisible = true;  
    }
  }

  viewFishSpeciesDetails(id: number) {
    this.router.navigate(['/fish-species-details/' + id]);
  }

  startDateInvalid() {
    let month = this.newFishForm.value.fishingBanStartMonth; 
    let day = this.newFishForm.value.fishingBanStartDay;
    
    if(((month == 2 || month == 4 || month == 6 || month == 9 || month == 11) && day == 31) || (month == 2 && day == 30) || day > 31) {
      return true;
    } else {
      return false; 
    } 
  }

  endDateInvalid() {
    let month = this.newFishForm.value.fishingBanEndMonth; 
    let day = this.newFishForm.value.fishingBanEndDay;
    
    if(((month == 2 || month == 4 || month == 6 || month == 9 || month == 11) && day == 31) || (month == 2 && day == 30) || day > 31) {
      return true;
    } else {
      return false; 
    } 
  }

  imageUpload(event: any) {
    // this.file = event.target.files[0];
    // console.log(this.file);
    
    
    
  }
  
}
