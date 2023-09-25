import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PenaltyService } from '../services/penalty.service';

@Component({
  selector: 'app-penalties-of-fisherman',
  templateUrl: './penalties-of-fisherman.component.html',
  styleUrls: ['./penalties-of-fisherman.component.css']
})
export class PenaltiesOfFishermanComponent {

  penalties : any = [];
  penaltiesOfFisherman: any = [];
  fishermanId: number = 0;
  imposePenaltyForm: FormGroup;

  constructor(
    private penaltyService: PenaltyService,
    private route: ActivatedRoute
  ) {
    this.imposePenaltyForm = new FormGroup({
      penaltyForm: new FormControl()
    });
  }

  ngOnInit() {
    this.fishermanId = Number(this.route.snapshot.paramMap.get('id'));
    this.getAllPenalties();
    this.getAllPenaltiesOfFisherman(this.fishermanId);  
  }

  getAllPenalties() {
    this.penaltyService.getAllPenalties().subscribe({
      next: data => {
        this.penalties = data;
      }
    });
  }

  getAllPenaltiesOfFisherman(fishermanId: number) {
    this.penaltyService.getAllPenalitesOfFisherman(fishermanId).subscribe({
      next: data => {
        this.penaltiesOfFisherman = data;
      }
    });
  }

  imposeAPenalty() {
    let penaltyId = this.imposePenaltyForm.value.penaltyForm.id;
    this.penaltyService.imposeAPenalty(penaltyId, this.fishermanId).subscribe({});
  }
}
