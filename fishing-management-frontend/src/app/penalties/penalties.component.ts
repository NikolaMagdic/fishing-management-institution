import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PenaltyService } from '../services/penalty.service';

@Component({
  selector: 'app-penalties',
  templateUrl: './penalties.component.html',
  styleUrls: ['./penalties.component.css']
})
export class PenaltiesComponent {

  penalties : any = [];
  penaltyForm: FormGroup;

  constructor(
    private penaltyService: PenaltyService,
    private router: Router
    ) {
      this.penaltyForm = new FormGroup({
        name: new FormControl(),
        description: new FormControl(),
        fine: new FormControl()
      });
    }

  ngOnInit() {
    this.getAllPenalties();
  }

  getAllPenalties() {
    this.penaltyService.getAllPenalties().subscribe({
      next: data => {
        this.penalties = data;
      }
    });
  }

  addPenalty() {
    let penalty = {
      "name": this.penaltyForm.value.name,
      "description": this.penaltyForm.value.description,
      "fine": this.penaltyForm.value.fine
    }
    this.penaltyService.addPenalty(penalty).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }

  penaltyDetails(id: number) {
    this.router.navigate(["/penalty/" + id]);
  }
}
