import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatchFormComponent } from './catch-form.component';

describe('CatchFormComponent', () => {
  let component: CatchFormComponent;
  let fixture: ComponentFixture<CatchFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatchFormComponent]
    });
    fixture = TestBed.createComponent(CatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
