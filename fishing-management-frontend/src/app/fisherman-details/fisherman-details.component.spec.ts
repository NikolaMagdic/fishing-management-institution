import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishermanDetailsComponent } from './fisherman-details.component';

describe('FishermanDetailsComponent', () => {
  let component: FishermanDetailsComponent;
  let fixture: ComponentFixture<FishermanDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishermanDetailsComponent]
    });
    fixture = TestBed.createComponent(FishermanDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
