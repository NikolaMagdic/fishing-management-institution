import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PenaltiesComponent } from './penalties.component';

describe('PenaltiesComponent', () => {
  let component: PenaltiesComponent;
  let fixture: ComponentFixture<PenaltiesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PenaltiesComponent]
    });
    fixture = TestBed.createComponent(PenaltiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
