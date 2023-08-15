import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingSpotDetailsComponent } from './fishing-spot-details.component';

describe('FishingSpotDetailsComponent', () => {
  let component: FishingSpotDetailsComponent;
  let fixture: ComponentFixture<FishingSpotDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishingSpotDetailsComponent]
    });
    fixture = TestBed.createComponent(FishingSpotDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
