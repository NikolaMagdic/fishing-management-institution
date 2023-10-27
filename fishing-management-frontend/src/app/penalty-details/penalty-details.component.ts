import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PenaltyService } from '../services/penalty.service';

@Component({
  selector: 'app-penalty-details',
  templateUrl: './penalty-details.component.html',
  styleUrls: ['./penalty-details.component.css']
})
export class PenaltyDetailsComponent {

  penalty: any;
  penaltyForm: FormGroup;
  formDisabled: boolean = true;
  updateButtonVisible: boolean = true;
  adminLoggedIn: boolean = false;

  constructor(
    private penaltyService: PenaltyService,
    private route: ActivatedRoute
    ) {
    this.penaltyForm = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      fine: new FormControl()
    });
  }

  ngOnInit() {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.getPenalty(id);

    const role = localStorage.getItem('role');
    if(role == "ROLE_ADMIN") {
      this.adminLoggedIn = true;
    }
  }

  enableForm() {
    this.updateButtonVisible = false;
    this.formDisabled = false;
  }

  getPenalty(id: number) {
    this.penaltyService.getPenaltyById(id).subscribe({
      next: data => {
        this.penalty = data;
        this.penaltyForm.setValue({
          name: this.penalty.name,
          description: this.penalty.description,
          fine: this.penalty.fine
        });
      }
    });
  }

  updatePenalty() {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    let penalty = {
      "name": this.penaltyForm.value.name,
      "description": this.penaltyForm.value.description,
      "fine": this.penaltyForm.value.fine
    }
    this.penaltyService.updatePenalty(id, penalty).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }
}
