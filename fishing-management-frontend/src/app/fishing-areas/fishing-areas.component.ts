import {Component, OnInit} from '@angular/core';

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
    fishingAreas: any = [
        {
            "id": 1,
            "name": "Borkovac",
            "type": "Jezero",
            "image": "https://www.gradnja.rs/wp-content/uploads/2022/04/borkovac-ruma-naslovna.jpg"
        },
        {
            "id": 2,
            "name": "Sava",
            "type": "Reka",
            "image": "https://turizamusrbiji.rs/wp-content/uploads/2014/10/belgrade-sava-river.jpg"
        }
    ]

    // Funkcija koja se izvrsava kada se prvi put inicijalizuje komponenta, ne znam u cemu je razlika sa ngOnInit
    constructor() {
        this.filteredFishingAreas = this.fishingAreas;
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

    onRatingClicked(message: string) : void {
        this.pozdravnaPoruka = this.pozdravnaPoruka + " " + message;
    }

    ngOnInit() : void {
        console.log("Ova metoda se poziva automatski kada se komponenta ucita i na ovom mestu se radi inicijalizacija podataka");
    }
}