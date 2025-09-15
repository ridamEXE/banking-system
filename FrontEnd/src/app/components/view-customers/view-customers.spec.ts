import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCustomersComponent } from './view-customers';

describe('ViewCustomers', () => {
  let component: ViewCustomersComponent;
  let fixture: ComponentFixture<ViewCustomersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewCustomersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewCustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
