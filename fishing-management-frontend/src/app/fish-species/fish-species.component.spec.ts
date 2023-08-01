import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishSpeciesComponent } from './fish-species.component';

describe('FishSpeciesComponent', () => {
  let component: FishSpeciesComponent;
  let fixture: ComponentFixture<FishSpeciesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishSpeciesComponent]
    });
    fixture = TestBed.createComponent(FishSpeciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
