import { Component, EventEmitter, Input, OnChanges, Output } from "@angular/core";
// Primer ugnjezdene komponente
@Component({
    selector: 'fm-rating',
    templateUrl: './rating.component.html',
    styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnChanges{
    @Input() rating: number = 0;
    starWidth: number = 0;
    @Output() ratingClicked: EventEmitter<string> = new EventEmitter<string>();

    ngOnChanges(): void {
        this.starWidth = this.rating * 86/5;
    }

    onClick(): void {
        this.ratingClicked.emit(this.rating.toString());
    }
}