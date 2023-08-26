import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KeeperService } from '../services/keeper.service';

@Component({
  selector: 'app-keepers',
  templateUrl: './keepers.component.html',
  styleUrls: ['./keepers.component.css']
})
export class KeepersComponent {

  keepers: any = [];

  constructor(private keeperService: KeeperService,
              private router: Router) {}

  ngOnInit() {
    this.keeperService.getKeepers().subscribe({
      next: data => {
        this.keepers = data;
      }
    })
  }

  keeperDetails(id: number) {
    this.router.navigate(["/keeper/" + id]);
  }
}
