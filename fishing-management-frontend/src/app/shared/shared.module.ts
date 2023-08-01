import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RatingComponent } from './rating.component';
import { CustomPipe } from './custom-pipe-example.pipe';

// Primer modula. Angular aplikacija moze biti podeljena na vise Feature Modula (npr FishingAreasModule, FishSpeciesModule...)
// kako bi se bolje razgranicile funkcionalnosti i kako se nebi sve trpalo u AppModule koji mora postojati i kod koga se ostali 
// moduli importuju. Postoje i ovakvi Shared Moduli koji prvenstveno sluze za izdvajanje 
// zajednickih funkcionalnosti (Na isti nacin se pisu razlika je samo semanticka)

@NgModule({
  declarations: [
    RatingComponent, // Ovde se deklarisu sve komponente, direktive i pajpovi u okviru modula (isto kao i u AppModule)
    CustomPipe
  ],
  imports: [
    CommonModule // Importujemo samo module neophodne za komponente koje su definisane u okviru ovog modula
  ],
  exports: [
    RatingComponent, // Exportujemo komponente kako bi drugi moduli koji importuju ovaj modul mogle da ih koriste
    CustomPipe
    // FormsModule - mogu se reexportovati i moduli koji nisu importovani i korisceni u okviru ovog modula, ali se koriste u okviru
    // drugih, pa je SharedModul zgodno mesto za njihovu definiciju
  ]
})
export class SharedModule { }
