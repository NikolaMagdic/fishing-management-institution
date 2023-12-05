import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { windowToggle } from 'rxjs';
import { FishSpecies } from '../models/fish-species';
import { FishingArea } from '../models/fishing-area';
import { FishSpeciesService } from '../services/fish-species.service';
import { FishingAreaService } from '../services/fishing-area.service';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-fishing-area-details',
  templateUrl: './fishing-area-details.component.html',
  styleUrls: ['./fishing-area-details.component.css']
})
export class FishingAreaDetailsComponent implements OnInit{
  fishingArea: any | undefined;
  fishSpeciesInArea: FishSpecies[] | any = [];
  fishSpeciesNotInArea: FishSpecies[] | any = [];
  selectedFish: FishSpecies | any;
  adminLoggedIn: boolean = false;
  areaForm: FormGroup;
  image: any;
  viewChildsButtonShown = false;

  constructor(private _route: ActivatedRoute, 
              private _fishingAreaService: FishingAreaService,
              private _router: Router,
              private fishSpeciesService: FishSpeciesService,
              private imageService: ImageService) { 
                this.areaForm = new FormGroup({
                  name: new FormControl(),
                  description: new FormControl(),
                  type: new FormControl()
                });
              }

  ngOnInit() {
    // Uz pomoc ActivatedRoute izvlacimo vrednost id-ja iz route
    // Moramo castovati u Number jer je parametar tipa String
    let id = Number(this._route.snapshot.paramMap.get('id'));
    this._fishingAreaService.getFishingAreaById(id).subscribe({
      next: fishingArea => {
        this.fishingArea = fishingArea;
        this.areaForm.setValue({
          name: this.fishingArea.name,
          description: this.fishingArea.description,
          type: this.fishingArea.type
        });
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

    const role = localStorage.getItem('role');
    if(role == "ROLE_ADMIN") {
      this.adminLoggedIn = true;  
    }

    this.getChildsOfFishingArea(id);
    
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

  showFishingSpots() {
    let id = Number(this._route.snapshot.paramMap.get('id'));
    this._router.navigate(['/fishing-spots/' + id]);
  }

  showChildAreas() {
    let id = Number(this._route.snapshot.paramMap.get('id'));
    this._router.navigate(['/fishing-areas/' + id + "/child"]);
  }

  removeFishFromArea(fishId: number) {
    this._fishingAreaService.removeFishSpeciesFromArea(this.fishingArea.id, fishId).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }

  updateFishingArea() {
    this.fishingArea.name = this.areaForm.value.name;
    this.fishingArea.description = this.areaForm.value.description;
    this.fishingArea.type = this.areaForm.value.type;
    if(this.image) {
      this.imageService.uploadImage(this.image).subscribe({
        next: imagePath => {
          this.fishingArea.image = imagePath as string;
          this._fishingAreaService.updateFishingArea(this.fishingArea.id, this.fishingArea).subscribe({
            next: () => {
              window.location.reload();
            }
          });
        }
      });
    } else {
      this._fishingAreaService.updateFishingArea(this.fishingArea.id, this.fishingArea).subscribe({
        next: () => {
          window.location.reload();
        }
      });
    }
  }

  getChildsOfFishingArea(id: number) {
    this._fishingAreaService.getPartsOfFishingArea(id).subscribe({
      next: data => {
        let fishingAreas = data as FishingArea[];
        if(fishingAreas.length != 0) {
          this.viewChildsButtonShown = true;
        }
      }
    });
  }

  processFile(imageFile: any) {
    const file: File = imageFile.files[0];
    this.image = file;
  }  

}
