import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ThankYouRegistrationComponent } from './thank-you-registration.component';

describe('ThankYouRegistrationComponent', () => {
  let component: ThankYouRegistrationComponent;
  let fixture: ComponentFixture<ThankYouRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ThankYouRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ThankYouRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
