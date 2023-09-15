import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Fisherman } from '../models/fisherman';
import { CatchService } from '../services/catch.service';
import { FishermanService } from '../services/fisherman.service';

@Component({
  selector: 'app-fishermans',
  templateUrl: './fishermans.component.html',
  styleUrls: ['./fishermans.component.css']
})
export class FishermansComponent {

  fishermans: Fisherman[] = [];

  constructor(
    private fishermanService: FishermanService,
    private catchService: CatchService,
    private router: Router
    ) {}

  ngOnInit() {
    this.fishermanService.getAllFishermans().subscribe({
      next: data => {
        this.fishermans = data as Fisherman[];
      }
    });
  }

  catchesOfFisherman(fishermanId: number) {
    this.router.navigate(["/fisherman/" + fishermanId + "/catches"]);
  }

  getFishermansWithNonConfirmedCatches() {
    this.fishermanService.getFishermansWithNonConfirmedCatches().subscribe({
      next: data => {
        this.fishermans = data as Fisherman[];
      }
    });
  }

}
