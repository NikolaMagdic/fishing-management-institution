import {Component} from '@angular/core';

@Component({
    selector: 'fishing-areas',
    templateUrl: './fishing-areas.component.html'
})
export class FishingAreasListComponent {
    pozdravnaPoruka: string = "Sve nase ribolovne vode:";
    imageWidth: number = 200;
    imagesShown: boolean = true;
    loggedInUserName: string = "Dzoni";

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

    toggleImages() : void {
        this.imagesShown = !this.imagesShown;
    }
}