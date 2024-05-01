import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  catch: Catch = new Catch(0, [], null);
  catchForm: FormGroup;
  catchItemForm: FormGroup;

  // Da ne mogu da se dodaju stavke dok se ne izabere ribolovna voda (jer se na osnovu vode traze ponudjene vrste riba)
  addItemDisabled: boolean = true;
  alertShown = false;

  @ViewChild('catchItemModalClose') catchItemModelClose: ElementRef | any;
  // Za success modal
  @ViewChild('openModal') openModal: ElementRef | any;
  
  constructor(private catchService: CatchService,
              private fishSpeciesService: FishSpeciesService,
              private fishingAreaService: FishingAreaService) {
                this.catchForm = new FormGroup({
                  selectedArea: new FormControl(),
                  date: new FormControl()
                }),
                this.catchItemForm = new FormGroup({
                  quantity: new FormControl(),
                  weight: new FormControl()
                })
              }
              

  ngOnInit() {
    this.getFishingAreas();
    this.getFishSpecies();

    var c = localStorage.getItem("catch");
    var area = localStorage.getItem("selectedArea");
    if(c != null) {
      this.catch = JSON.parse(c);
      this.catchForm.controls['date'].setValue(this.catch.date);
    }
    if(area != null) {
      this.catchForm.controls['selectedArea'].setValue(JSON.parse(area));
      
      console.log(this.catchForm.value.selectedArea);
    }
    
  }

  getFishingAreas() {
    this.fishingAreaService.getFishingAreas().subscribe({
      next: data => {
        this.fishingAreas = data as FishingArea[];
      }
    }); 
  }

  getFishSpecies() {
    this.fishSpeciesService.getFishSpecies().subscribe({
      next: data => {
        this.fishSpecies = data as FishSpecies[];
      }
    });
  }

  addCatchItem() {

    // Mora biti uneta bar jedno od dve stavke: kolicina ili tezina
    if(!(this.catchItemForm.value.quantity || this.catchItemForm.value.weight)) {
      this.alertShown = true;
      return;
    }

    var newItem = new CatchItem(
      this.selectedFish.id, 
      this.catchItemForm.value.quantity,
      this.catchItemForm.value.weight
    );
      
    // Mora ovako jer ako se doda samo newItem to je samo referenca i svi objekti u nizu ce imati trenutnu vrednost newItem, bice isti
    // Na ovaj nacin pravim novi objekat pre dodavanja u niz
    this.catch.catchItems.push(JSON.parse(JSON.stringify(newItem)));

    // Resetujemo formu
    this.catchItemForm.reset();
    this.alertShown = false;
    this.catchItemModelClose.nativeElement.click();
    
    // Cuvam trenutno dodate stavke ulova u lokalnu memoriju - prakticno pravim Cart
    this.catch.date = this.catchForm.value.date;
    localStorage.setItem("catch", JSON.stringify(this.catch));
    localStorage.setItem("selectedArea", JSON.stringify(this.catchForm.value.selectedArea));
  }

  createCatch() {
    this.catch.fishingAreaId = this.catchForm.value.selectedArea;
    this.catch.date = this.catchForm.value.date;
    this.catchService.createCatch(this.catch).subscribe({
      next: () => {
        this.openModal.nativeElement.click();
        localStorage.removeItem("catch");
        localStorage.removeItem("selectedArea");
      }
    });
  }

  findFishNameById(fishId: number) {
    var filteredFish = this.fishSpecies.filter(fish => fish.id == fishId);
    return filteredFish[0].name;
  }

  removeCatchItem(index: number) {
    console.log(index);
    this.catch.catchItems.splice(index, 1);

    localStorage.setItem("catch", JSON.stringify(this.catch));
  }  

  checkIfNoble() {
    if(this.selectedFish.category == "NOBLE") {
      this.catchItemForm.controls['quantity'].setValue(1);
    } else {
      this.catchItemForm.controls['quantity'].reset();
    }
  }

  reloadPage() {
    window.location.reload();
  }
}
