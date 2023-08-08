import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishSpeciesDetailsComponent } from './fish-species-details.component';

describe('FishSpeciesDetailsComponent', () => {
  let component: FishSpeciesDetailsComponent;
  let fixture: ComponentFixture<FishSpeciesDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishSpeciesDetailsComponent]
    });
    fixture = TestBed.createComponent(FishSpeciesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
