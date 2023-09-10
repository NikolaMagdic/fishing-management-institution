import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RegistrationRequest } from '../models/registration-request';
import { KeeperService } from '../services/keeper.service';

@Component({
  selector: 'app-keeper-form',
  templateUrl: './keeper-form.component.html',
  styleUrls: ['./keeper-form.component.css']
})
export class KeeperFormComponent {

  keeperForm: FormGroup;
  @ViewChild('openModal') openModal: ElementRef | any;
  formDisabled: boolean = false;
  keeper: any;

  constructor(private keeperService: KeeperService,
              private route: ActivatedRoute) { 
    this.keeperForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl(),
      firstName: new FormControl(),
      lastName: new FormControl(),
      dateOfBirth: new FormControl()
    });
  }

  registerKeeper() {
    var registration = new RegistrationRequest(
      this.keeperForm.value.username,
      this.keeperForm.value.password,
      this.keeperForm.value.password,
      this.keeperForm.value.firstName,
      this.keeperForm.value.lastName,
      this.keeperForm.value.dateOfBirth,
      "", "", ""
    );
    this.keeperService.registerKeeper(registration).subscribe({
      next: () => {
        this.openModal.nativeElement.click();
      }
    });
  }
}
