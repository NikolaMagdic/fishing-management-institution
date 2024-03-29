import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RegistrationKeeper } from '../models/registration-keeper';
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
  passwordsMatch: boolean = false;
  passwordLengthOk: boolean = false;

  constructor(private keeperService: KeeperService,
              private route: ActivatedRoute) { 
    this.keeperForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl(),
      confirmPassword: new FormControl(),
      firstName: new FormControl(),
      lastName: new FormControl(),
      dateOfBirth: new FormControl(),
      licenseNumber: new FormControl()
    });
  }

  registerKeeper() {
    var registration = new RegistrationKeeper(
      this.keeperForm.value.username,
      this.keeperForm.value.password,
      this.keeperForm.value.confirmPassword,
      this.keeperForm.value.firstName,
      this.keeperForm.value.lastName,
      this.keeperForm.value.dateOfBirth,
      this.keeperForm.value.licenseNumber
    );
    this.keeperService.registerKeeper(registration).subscribe({
      next: () => {
        this.openModal.nativeElement.click();
      }
    });
  }

  reloadPage() {
    window.location.reload();
  }

  checkIfPasswordsMatch() {
    if(this.keeperForm.value.password === this.keeperForm.value.confirmPassword) {
      this.passwordsMatch = true;
    } else {
      this.passwordsMatch = false;
    }

    if(this.keeperForm.value.password.length < 8) {
      this.passwordLengthOk = false; 
    } else {
      this.passwordLengthOk = true;
    }
  }
}
