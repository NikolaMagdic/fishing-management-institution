import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingAreaCatchesComponent } from './fishing-area-catches.component';

describe('FishingAreaCatchesComponent', () => {
  let component: FishingAreaCatchesComponent;
  let fixture: ComponentFixture<FishingAreaCatchesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishingAreaCatchesComponent]
    });
    fixture = TestBed.createComponent(FishingAreaCatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
