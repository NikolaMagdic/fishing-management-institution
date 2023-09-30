import { Pipe } from "@angular/core";
@Pipe({
    name: 'fishSpeciesPipe'
})
export class FishSpeciesPipe {
    transform(value: string) : string {
        if(value === "NOBLE") {
            return "Plemenita";
        } else if (value === "INDIGENOUS") {
            return "Autohtona";
        } else {
            return "Alohtona";
        }
    }
}