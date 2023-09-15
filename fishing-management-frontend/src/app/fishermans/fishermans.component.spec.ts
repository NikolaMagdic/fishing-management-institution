import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishermansComponent } from './fishermans.component';

describe('FishermansComponent', () => {
  let component: FishermansComponent;
  let fixture: ComponentFixture<FishermansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishermansComponent]
    });
    fixture = TestBed.createComponent(FishermansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
