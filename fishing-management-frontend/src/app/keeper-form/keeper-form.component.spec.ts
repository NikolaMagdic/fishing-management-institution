import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeeperFormComponent } from './keeper-form.component';

describe('KeeperFormComponent', () => {
  let component: KeeperFormComponent;
  let fixture: ComponentFixture<KeeperFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KeeperFormComponent]
    });
    fixture = TestBed.createComponent(KeeperFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
