import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroTitleComponent } from './hero-title.component';

describe('HeroTitleComponent', () => {
  let component: HeroTitleComponent;
  let fixture: ComponentFixture<HeroTitleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeroTitleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
