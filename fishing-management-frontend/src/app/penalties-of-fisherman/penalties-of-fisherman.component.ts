import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FishingAreaService } from '../services/fishing-area.service';
import { KeeperService } from '../services/keeper.service';
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
  keeperLoggedIn = false;
  areas: any = [];

  constructor(
    private penaltyService: PenaltyService,
    private fishingAreaService: FishingAreaService,
    private route: ActivatedRoute
  ) {
    this.imposePenaltyForm = new FormGroup({
      penaltyForm: new FormControl(),
      report: new FormControl(),
      date: new FormControl(),
      area: new FormControl()
    });
  }

  ngOnInit() {
    this.fishermanId = Number(this.route.snapshot.paramMap.get('id'));
    this.getAllPenalties();
    this.getAllPenaltiesOfFisherman(this.fishermanId);
    this.getAllFishingAreasThatKeeperKeeps();
    
    const role = localStorage.getItem('role');
    if(role == 'ROLE_KEEPER') {
      this.keeperLoggedIn = true;
    }
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

  getAllFishingAreasThatKeeperKeeps() {
    let keeperId = Number(localStorage.getItem('correspondingTableId'));
    this.fishingAreaService.getFishingAreasManagedByKeeper(keeperId).subscribe({
      next: data => {
        this.areas = data;
      }
    });
  }

  imposeAPenalty() {
    let penaltyId = this.imposePenaltyForm.value.penaltyForm.id;
    let areaId = this.imposePenaltyForm.value.area.id;
    let penalized = {
      "penaltyId": penaltyId,
      "fishermanId": this.fishermanId,
      "date": this.imposePenaltyForm.value.date,
      "report": this.imposePenaltyForm.value.report,
      "areaId": areaId
    }
    this.penaltyService.imposeAPenalty(penalized).subscribe({
      next: () => {
        this.getAllPenaltiesOfFisherman(this.fishermanId);
      }
    });
  }
}
