import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CatchService } from '../services/catch.service';
import { Chart } from 'chart.js/auto';
import { toStringHDMS } from 'ol/coordinate';
import { FishingAreaService } from '../services/fishing-area.service';
import { FishingArea } from '../models/fishing-area';

@Component({
  selector: 'app-fishing-area-catches',
  templateUrl: './fishing-area-catches.component.html',
  styleUrls: ['./fishing-area-catches.component.css']
})
export class FishingAreaCatchesComponent {

  chart: any = [];
  chart2: any = [];
  catches: any = [];
  nobleCatches: any = [];
  year: number = new Date().getFullYear();
  fishNames: any;
  fishNumber: any;
  fishingAreaName: string = "";

  constructor(
    private catchService: CatchService,
    private fishingAreaService: FishingAreaService,
    private route: ActivatedRoute
  ) {
    
  }

  ngOnInit() {
    let areaId = Number(this.route.snapshot.paramMap.get('id'));
  
    this.catchService.getYearlyCatchesInFishingArea(areaId, new Date().getFullYear()).subscribe({
      next: data => {
        this.catches = data;
      }
    });

    this.catchService.getYearlyCatchesOfNobleFishSpeciesInFishingArea(areaId, new Date().getFullYear()).subscribe({
      next: data => {
        this.nobleCatches = data;
        this.fishNames = this.nobleCatches.map((nobleCatches: any) => nobleCatches.fishSpeciesName);
        this.fishNumber = this.nobleCatches.map((nobleCatches: any) => nobleCatches.yearQuantity);
      
        this.drawChart();
        this.drawChart2();
      }
    });

    this.fishingAreaService.getFishingAreaById(areaId).subscribe({
      next: data => {
        let fishingArea = data as FishingArea;
        this.fishingAreaName = fishingArea.name;
      }
    });

  }

  changeYear() {
    let areaId = Number(this.route.snapshot.paramMap.get('id'));
  
    this.catchService.getYearlyCatchesInFishingArea(areaId, this.year).subscribe({
      next: data => {
        this.catches = data;
      }
    });

    this.catchService.getYearlyCatchesOfNobleFishSpeciesInFishingArea(areaId, this.year).subscribe({
      next: data => {
        this.nobleCatches = data;
        this.fishNames = this.nobleCatches.map((nobleCatches: any) => nobleCatches.fishSpeciesName);
        this.fishNumber = this.nobleCatches.map((nobleCatches: any) => nobleCatches.yearQuantity);
        
        this.updateChart();
      }
    });
  }

  drawChart() {
    this.chart = new Chart(
      "chart",
      {
        type: 'pie',
        data: {
          labels: this.fishNames,
          datasets: [{
            label: 'Broj primeraka',
            data: this.fishNumber,
            backgroundColor: [
              'rgb(255, 205, 86)',
              'rgb(54, 162, 235)',
              'rgb(255, 99, 132)',
              'rgb(23, 191, 68)',
              'rgb(181, 28, 184)'
            ],
            hoverOffset: 4
          }]
        }
      }
    );
  }

  drawChart2() {
    this.chart2 = new Chart(
      "chart-2",
      {
        type: 'bar',
        data: {
          labels: this.fishNames,
          datasets: [{
            label: "Broj primeraka",
            data: this.fishNumber,
            backgroundColor: [
              'rgb(54, 162, 235)'
            ]
          }]
        }
      }
    );
  }

  updateChart() {
    this.chart.data.labels = this.fishNames;
    this.chart.data.datasets[0].data = this.fishNumber;
    this.chart.update();
    this.chart2.data.labels = this.fishNames;
    this.chart2.data.datasets[0].data = this.fishNumber;
    this.chart2.update();
  }

}
