import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Catch } from '../models/catch';
import { CatchService } from '../services/catch.service';

@Component({
  selector: 'app-catches',
  templateUrl: './catches.component.html',
  styleUrls: ['./catches.component.css']
})
export class CatchesComponent {

  catches: any = [];
  keeperLoggedIn = false;

  constructor(
    private catchService: CatchService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    let fishermanId = Number(this.route.snapshot.paramMap.get('id'));

    this.catchService.getAllCatchesOfFisherman(fishermanId).subscribe({
      next: data => {
        this.catches = data;
        console.log(this.catches);
      }
    });

    const role = localStorage.getItem('role');
    if(role == "ROLE_KEEPER") {
      this.keeperLoggedIn = true;  
    }
  }

  confirmCatch(itemId: number) {
    this.catchService.confirmCatch(itemId).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }
}
