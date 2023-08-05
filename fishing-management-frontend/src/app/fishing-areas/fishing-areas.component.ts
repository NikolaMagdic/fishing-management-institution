import {Component, OnInit} from '@angular/core';
import { FishingArea } from '../models/fishing-area';
import { FishingAreaService } from '../services/fishing-area.service';

@Component({
    selector: 'fishing-areas',
    templateUrl: './fishing-areas.component.html',
    styleUrls: ['./fishing-areas.component.css']
})
export class FishingAreasListComponent {
    pozdravnaPoruka: string = "Sve nase ribolovne vode:";
    imageWidth: number = 200;
    imagesShown: boolean = true;
    filter: string = "";

    filteredFishingAreas: any = [];

    // Mozemo napraviti interface fishingAreas kako bismo ga tipizirali i na taj nacin imali odmah i neku vrstu validacije, za sad to necu jer je to cist typescritp
    fishingAreas: any = []

    errorMessage: string = "";
    fishingArea: FishingArea = new FishingArea(0, "", "", "", "");

    // Konstruktor koji se poziva prilikom inicijalizacije komponente, izvrsava se pre ngOnInit
    constructor(private _fishingAreaService: FishingAreaService) {
        // Ovo gore je sintaksni secer koji uproscava konstruktor koji je inace u osnovi isti kao i u Javi
    }

    toggleImages() : void {
        this.imagesShown = !this.imagesShown;
    }

    // Filtracija
    doFilter() {
        this.filter =  this.filter.toLocaleLowerCase();
        if(this.filter === "") {
            this.filteredFishingAreas = this.fishingAreas;
        }
        this.filteredFishingAreas = this.fishingAreas.filter((fishingArea: { name: string; }) => 
            fishingArea.name.toLocaleLowerCase().indexOf(this.filter) !== -1);
        
    }

    onFilterChange(value: string) {
        this.filter = value;
    }

    onRatingClicked(message: string) : void {
        this.pozdravnaPoruka = this.pozdravnaPoruka + " " + message;
    }

    createFishingArea() { // Kod post zahteva mora subscribe inace nece biti pozvan
        this._fishingAreaService.createFishingArea(this.fishingArea).subscribe({
            next: () => {
                window.location.reload();
            }
        });
    }

    ngOnInit() : void {
        console.log("Ova metoda se poziva automatski kada se komponenta ucita i na ovom mestu se radi inicijalizacija podataka");
        this._fishingAreaService.getFishingAreas().subscribe({
            next: fishingAreas => { 
                this.fishingAreas = fishingAreas,
                this.filteredFishingAreas = this.fishingAreas},
            error: err => this.errorMessage = err
        });
                
       
    }
}