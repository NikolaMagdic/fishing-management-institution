import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { SubscriptionLog } from 'rxjs/internal/testing/SubscriptionLog';
import { FishingArea } from '../models/fishing-area';
import { FishingAreaService } from '../services/fishing-area.service';
import { ImageService } from '../services/image.service';

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
    filterName: string = "";
    filterType: string = "Svi tipovi ribolovnih voda";

    // Mozemo napraviti interface fishingAreas kako bismo ga tipizirali i na taj nacin imali odmah i neku vrstu validacije, za sad to necu jer je to cist typescript
    fishingAreas: any = []

    errorMessage: string = "";
    fishingArea: FishingArea = new FishingArea(0, "", "", "", "");

    isAddButtonVisible = false;
    image: any;
    @ViewChild('openModal') openModal: ElementRef | any;
    // Konstruktor koji se poziva prilikom inicijalizacije komponente, izvrsava se pre ngOnInit
    constructor(private _fishingAreaService: FishingAreaService,
                private imageService: ImageService,
                private router: Router) {
        // Ovo gore je sintaksni secer koji uproscava konstruktor koji je inace u osnovi isti kao i u Javi
    }

    toggleImages() : void {
        this.imagesShown = !this.imagesShown;
    }

    // Filtracija
    // doFilter() {
    //     this.filter =  this.filter.toLocaleLowerCase();
    //     if(this.filter === "") {
    //         this.filteredFishingAreas = this.fishingAreas;
    //     }
    //     this.filteredFishingAreas = this.fishingAreas.filter((fishingArea: { name: string; }) => 
    //         fishingArea.name.toLocaleLowerCase().indexOf(this.filter) !== -1);
        
    // }

    filterAreas() {
        if(!this.filterName && this.filterType === "Svi tipovi ribolovnih voda") {
            this.filteredFishingAreas = this.fishingAreas;
        }

        this.filteredFishingAreas = this.fishingAreas.filter(
            (area: { name: string; type: string}) => 
            ((area?.name.toLowerCase()).includes(this.filterName.toLowerCase()) &&
            (area?.type === this.filterType || this.filterType === "Svi tipovi ribolovnih voda"))
        );
    }

    onFilterChange(value: string) {
        this.filter = value;
    }

    onRatingClicked(message: string) : void {
        this.pozdravnaPoruka = this.pozdravnaPoruka + " " + message;
    }

    createFishingArea() { // Kod post zahteva mora subscribe inace nece biti pozvan
        
        if(this.image) {
            // Slika je uneta
            this.imageService.uploadImage(this.image).subscribe({
                next: imagePath => {
                    this.fishingArea.image = imagePath as string;
                    this._fishingAreaService.createFishingArea(this.fishingArea).subscribe({
                        next: () => {
                            this.openModal.nativeElement.click();
                        }
                    });
                }
            });
        } else {
            // Slika nije uneta
            this._fishingAreaService.createFishingArea(this.fishingArea).subscribe({
                next: () => {
                   this.openModal.nativeElement.click();
                }
            });
        }  

    }
    
    showFishingAreaDetails(fishingAreaId: number) {
        this.router.navigate(["/fishing-areas/" + fishingAreaId])
    }

    processFile(imageFile: any) {
        const file: File = imageFile.files[0];
        this.image = file;
    }

    reloadPage() {
        window.location.reload();
    }

    ngOnInit() : void {
        console.log("Ova metoda se poziva automatski kada se komponenta ucita i na ovom mestu se radi inicijalizacija podataka");
        this._fishingAreaService.getFishingAreas().subscribe({
            next: fishingAreas => { 
                this.fishingAreas = fishingAreas,
                this.filteredFishingAreas = this.fishingAreas},
            error: err => this.errorMessage = err
        });
                
        let role = localStorage.getItem('role');
        if(role == "ROLE_ADMIN") {
            this.isAddButtonVisible = true;
        }
    }
    
}