import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositMoneyComponent } from './deposit-money';

describe('DepositMoney', () => {
  let component: DepositMoneyComponent;
  let fixture: ComponentFixture<DepositMoneyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepositMoneyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepositMoneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
