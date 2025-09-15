import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCustomerAccountComponent } from './create-customer-account';

describe('CreateCustomerAccount', () => {
  let component: CreateCustomerAccountComponent;
  let fixture: ComponentFixture<CreateCustomerAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCustomerAccountComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCustomerAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
