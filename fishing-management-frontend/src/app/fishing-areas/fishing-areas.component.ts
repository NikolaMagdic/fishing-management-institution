import {Component} from '@angular/core';

@Component({
    selector: 'fishing-areas',
    templateUrl: './fishing-areas.component.html'
})
export class FishingAreasListComponent {
    pozdravna_poruka: string = "Sve nase ribolovne vode:";
    fishing_areas: any = [
        {
            "id": 1,
            "name": "Borkovac",
            "type": "Jezero"
        },
        {
            "id": 2,
            "name": "Sava",
            "type": "Reka"
        }
    ]
}