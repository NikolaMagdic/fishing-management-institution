import { Component, ElementRef, ViewChild, ɵdevModeEqual } from '@angular/core';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishSpecies } from '../models/fish-species';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-fish-species',
  templateUrl: './fish-species.component.html',
  styleUrls: ['./fish-species.component.css']
})
export class FishSpeciesComponent {
  
  fishSpecies: FishSpecies[] = [];
  newFishForm: FormGroup;
  addFishButtonVisible = false;
  imposibleDate = false;
  permanentFishingBan = false;
  image: any;
  @ViewChild('openModal') openModal: ElementRef | any;

  filteredFishSpecies: any = [];
  filterName: string = "";
  filterLatinName: string = "";
  filterCategory: string = "Sve kategorije";
  sortNameAsc: boolean = false;
  sortNameDesc: boolean = false;
  sortLatinNameAsc: boolean = false; 
  sortLatinNameDesc: boolean = false;

  constructor(private fishSpeciesService: FishSpeciesService,
              private imageService: ImageService,
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
                  description: new FormControl(),
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
      this.newFishForm.value.description,
      this.newFishForm.value.image
    );

    // Prvo upload-ujemo sliku 
    if(this.image) {
      this.imageService.uploadImage(this.image).subscribe({
        next: imageName => {
          newFish.image = imageName as string;
          // Pa zatim u callback funkciji podatke o novoj ribi
          this.fishSpeciesService.createFishSpecies(newFish).subscribe({
            next: () => {
              this.openModal.nativeElement.click();
            }
          });  
        }
      });
    } else {
      // Ako nije uneta slika samo podatke o ribi unosimo
      this.fishSpeciesService.createFishSpecies(newFish).subscribe({
        next: () => {
          this.openModal.nativeElement.click();
        }
      });  
    }

  }

  ngOnInit() : void {

    this.getAllFishSpecies();

    const role = localStorage.getItem('role');
    if(role == "ROLE_ADMIN") {
      this.addFishButtonVisible = true;  
    }
  }

  getAllFishSpecies() {
    this.fishSpeciesService.getFishSpecies().subscribe({
      next: data => {
        let fish = data as FishSpecies[];
        this.fishSpecies = fish;
        this.filteredFishSpecies = fish;
      }
    })
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

  permanentFishingBanChecked() {
    this.permanentFishingBan = !this.permanentFishingBan;
  }
  
  imageUpload(imageFile: any) {
    const file: File = imageFile.files[0];
    this.image = file;
  }
  
  filterFishSpecies() {
    if(!this.filterName && !this.filterLatinName && this.filterCategory === "Sve kategorije") {
      this.filteredFishSpecies = this.fishSpecies;
    }

    this.filteredFishSpecies = this.fishSpecies.filter(
      fish => ((fish?.name.toLowerCase())
      .includes(this.filterName.toLowerCase()) && 
      (fish?.latinName.toLowerCase())
      .includes(this.filterLatinName.toLowerCase()) && 
      (fish?.category === this.filterCategory || this.filterCategory === "Sve kategorije"))
    )
  }

  reloadPage() {
    this.getAllFishSpecies();
    this.newFishForm.reset();
  }

  sortFishSpeciesByName() {
    if(this.sortNameAsc) {
      this.filteredFishSpecies.sort((a: FishSpecies, b: FishSpecies) => 
        b.name.localeCompare(a.name)
      );  
      this.sortNameAsc = false;
      this.sortNameDesc = true;
    } else {
      this.filteredFishSpecies.sort((a: FishSpecies, b: FishSpecies) => 
        a.name.localeCompare(b.name)
      );
      this.sortNameAsc = true;
      this.sortNameDesc = false;
    }
  }

  sortFishSpeciesByLatinName() {
    if(this.sortLatinNameAsc) {
      this.filteredFishSpecies.sort((a: FishSpecies, b: FishSpecies) =>
        b.latinName.localeCompare(a.latinName)
      );
      this.sortLatinNameAsc = false;
      this.sortLatinNameDesc = true;
    } else {
      this.filteredFishSpecies.sort((a: FishSpecies, b: FishSpecies) =>
        a.latinName.localeCompare(b.latinName)
      );
      this.sortLatinNameAsc = true;
      this.sortLatinNameDesc = false;
    }
  }

}
