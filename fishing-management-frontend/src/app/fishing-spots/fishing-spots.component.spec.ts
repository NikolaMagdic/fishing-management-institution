import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingSpotsComponent } from './fishing-spots.component';

describe('FishingSpotsComponent', () => {
  let component: FishingSpotsComponent;
  let fixture: ComponentFixture<FishingSpotsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishingSpotsComponent]
    });
    fixture = TestBed.createComponent(FishingSpotsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
