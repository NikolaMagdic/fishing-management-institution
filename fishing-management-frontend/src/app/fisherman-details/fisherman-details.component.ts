import { formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { toStringHDMS } from 'ol/coordinate';
import { Fisherman } from '../models/fisherman';
import { FishermanService } from '../services/fisherman.service';

@Component({
  selector: 'app-fisherman-details',
  templateUrl: './fisherman-details.component.html',
  styleUrls: ['./fisherman-details.component.css']
})
export class FishermanDetailsComponent {

  fishermanForm: FormGroup;
  fisherman: Fisherman | any;
  updateButtonHidden: boolean = true;
  submitButtonHidden: boolean = true;
  fieldsetDisabled: boolean = true;

  constructor(
    private fishermanService: FishermanService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.fishermanForm = new FormGroup({
      firstName: new FormControl(),
      lastName: new FormControl(),
      dateOfBirth: new FormControl(),
      address: new FormControl(),
      city: new FormControl(),
      category: new FormControl({disabled: true}),
      registryNumber: new FormControl()
    });
  }

  ngOnInit() {
    this.getFisherMan();

    const role = localStorage.getItem('role');
    if(role == "ROLE_FISHERMAN") {
      this.updateButtonHidden = false;  
    }
  }

  getFisherMan() {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.fishermanService.getFishermanById(id).subscribe({
      next: data => {
        this.fisherman = data as Fisherman;
        this.fishermanForm.setValue({
          firstName: this.fisherman.firstName,
          lastName: this.fisherman.lastName,
          dateOfBirth: formatDate(this.fisherman.dateOfBirth, 'yyyy-MM-dd', 'en'),
          address: this.fisherman.address,
          city: this.fisherman.city,
          category: this.fisherman.category,
          registryNumber: this.fisherman.registryNumber
        });
      }
    })
  }

  updateFisherman() {
    let f = new Fisherman(this.fisherman.id,
      this.fishermanForm.value.firstName,
      this.fishermanForm.value.lastName,
      this.fishermanForm.value.dateOfBirth, 
      this.fishermanForm.value.address,
      this.fishermanForm.value.city,
      this.fishermanForm.value.category);
      f.registryNumber = this.fishermanForm.value.registryNumber;

      this.fishermanService.updateFisherman(f).subscribe({
        next: () => {
          window.location.reload();
        }
      })
  }

  enableForm() {
    this.updateButtonHidden = true;
    this.submitButtonHidden = false;
    this.fieldsetDisabled = false; 
  }

  showCatches() {
    this.router.navigate(["/fisherman/" + this.fisherman.id + "/catches"]);
  }

  showPenalties() {
    this.router.navigate(["/fisherman/" + this.fisherman.id + "/penalties"]);
  }
}
