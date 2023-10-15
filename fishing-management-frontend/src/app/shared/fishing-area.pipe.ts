import { Pipe } from "@angular/core";

@Pipe({
    name: 'fishingAreaPipe'
})
export class FishingAreaPipe {
    transform(value: string) : string {
        if(value === "RIVER") {
            return "Reka";
        } else if (value === "LAKE") {
            return "Jezero";
        } else if (value === "POND") {
            return "Bara"
        } else {
            return "Kanal"
        }
    }
}