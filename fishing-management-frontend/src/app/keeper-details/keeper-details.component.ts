import { formatDate } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FishingArea } from '../models/fishing-area';
import { Keeper } from '../models/keeper';
import { FishingAreaService } from '../services/fishing-area.service';
import { KeeperService } from '../services/keeper.service';

@Component({
  selector: 'app-keeper-details',
  templateUrl: './keeper-details.component.html',
  styleUrls: ['./keeper-details.component.css']
})
export class KeeperDetailsComponent {

  keeperForm: FormGroup;
  @ViewChild('openModalSuccess') openModalSuccess: ElementRef | any;
  @ViewChild('openModal') openModal: ElementRef | any;
  keeper: Keeper = new Keeper(0, "", "", new Date(), "");
  fieldsetDisabled: boolean = true;
  submitButtonHidden: boolean = true;
  changeButtonHidden: boolean = false;
  managedAreas: FishingArea[] | any = [];
  notManagedAreas: FishingArea[] | any = [];
  selectedArea: FishingArea | any;
  adminLoggedIn = false;

  constructor(private keeperService: KeeperService,
              private fishingAreaService: FishingAreaService,
              private route: ActivatedRoute) { 
    this.keeperForm = new FormGroup({
      firstName: new FormControl(),
      lastName: new FormControl(),
      dateOfBirth: new FormControl(),
      licenseNumber: new FormControl()
    });
  }

  ngOnInit() {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    
    this.keeperService.getKeeperById(id).subscribe({
      next: data => {
        console.log(data);
        this.keeper = data as Keeper;
        this.keeperForm.setValue({
          firstName: this.keeper.firstName,
          lastName: this.keeper.lastName,
          dateOfBirth: formatDate(this.keeper.dateOfBirth, 'yyyy-MM-dd', 'en'),
          licenseNumber: this.keeper.licenseNumber
        })
        console.log(this.keeper);
      }
    });

    this.fishingAreaService.getFishingAreasManagedByKeeper(id).subscribe({
      next: data => {
        this.managedAreas = data;
      }
    });

    this.fishingAreaService.getFishingAreasNotManagedByKeeper(id).subscribe({
      next: data => {
        this.notManagedAreas = data;
      }
    });

    const role = localStorage.getItem('role');
    if(role == "ROLE_ADMIN") {
      this.adminLoggedIn = true;
    }
  }

  enableForm() {
    this.changeButtonHidden = true;
    this.fieldsetDisabled = false;
    this.submitButtonHidden = false;
  }

  updateKeeper() {
    let k = new Keeper(this.keeper.id, 
      this.keeperForm.value.firstName, 
      this.keeperForm.value.lastName, 
      this.keeperForm.value.dateOfBirth,
      this.keeperForm.value.licenseNumber);
      
    this.keeperService.updateKeeper(k).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }

  addAreaToManage() {
    this.keeperService.addAreaToManage(this.keeper.id, this.selectedArea.id).subscribe({
      next: () => {
        window.location.reload();
      }
    })
  }

  removeManagingArea(areaId: number) {
    this.keeperService.removeManagedArea(this.keeper.id, areaId).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }

}
