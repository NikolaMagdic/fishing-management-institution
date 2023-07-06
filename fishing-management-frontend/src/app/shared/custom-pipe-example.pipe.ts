import { Pipe } from "@angular/core";
// Primer pravljenja proizvoljnog pipe, menja svaki - sa +
// Pozivanje: customPipe: '-'
@Pipe({
    name: 'customPipe'
})
export class CustomPipe {
    transform(value: string, character: string) : string {
        return value.replace(character, '+');
    }
}