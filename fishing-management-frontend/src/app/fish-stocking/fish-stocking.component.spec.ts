import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishStockingComponent } from './fish-stocking.component';

describe('FishStockingComponent', () => {
  let component: FishStockingComponent;
  let fixture: ComponentFixture<FishStockingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishStockingComponent]
    });
    fixture = TestBed.createComponent(FishStockingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
