import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PenaltiesOfFishermanComponent } from './penalties-of-fisherman.component';

describe('PenaltiesOfFishermanComponent', () => {
  let component: PenaltiesOfFishermanComponent;
  let fixture: ComponentFixture<PenaltiesOfFishermanComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PenaltiesOfFishermanComponent]
    });
    fixture = TestBed.createComponent(PenaltiesOfFishermanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
