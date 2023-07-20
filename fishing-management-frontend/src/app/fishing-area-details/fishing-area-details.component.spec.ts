import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingAreaDetailsComponent } from './fishing-area-details.component';

describe('FishingAreaDetailsComponent', () => {
  let component: FishingAreaDetailsComponent;
  let fixture: ComponentFixture<FishingAreaDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishingAreaDetailsComponent]
    });
    fixture = TestBed.createComponent(FishingAreaDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
