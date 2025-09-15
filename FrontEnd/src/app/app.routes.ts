import { authGuard } from './guards/auth-guard'; // <-- Import the guard
import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home';
import { LoginComponent } from './components/login/login';
import { LayoutComponent } from './components/layout/layout';
import { CustomerRegistrationComponent } from './components/customer-registration/customer-registration';
import { RegisterEmployeeComponent } from './components/register-employee/register-employee';
import { ResetPasswordComponent } from './components/reset-password/reset-password';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard';
import { ViewEmployeesComponent } from './components/view-employees/view-employees';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee';
import { CreateCustomerAccountComponent } from './components/create-customer-account/create-customer-account';
import { ViewCustomersComponent } from './components/view-customers/view-customers';
import { EditCustomerComponent } from './components/edit-customer/edit-customer';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard';
import { DepositMoneyComponent } from './components/deposit-money/deposit-money';
import { WithdrawMoneyComponent } from './components/withdraw-money/withdraw-money';
import { ViewAllCustomersComponent } from './components/view-all-customers/view-all-customers';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard';
import { ViewBalanceComponent } from './components/view-balance/view-balance';
import { TransferMoneyComponent } from './components/transfer-money/transfer-money';
import { AccountStatementComponent } from './components/account-statement/account-statement';
import { UpdateDetailsComponent } from './components/update-details/update-details';

export const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {path: '', redirectTo: 'home', pathMatch: 'full'},
            {path: 'home', component: HomeComponent},
            {path: 'login', component: LoginComponent},
            {path: 'customer-registration', component: CustomerRegistrationComponent},
            { path: 'register-employee', component: RegisterEmployeeComponent ,canActivate:[authGuard]},
            { path: 'reset-password', component: ResetPasswordComponent },
            
            { path: 'manager-dashboard', component: ManagerDashboardComponent ,canActivate:[authGuard]},
            { path: 'view-employees', component: ViewEmployeesComponent ,canActivate:[authGuard]},
            { path: 'edit-employee/:id', component: EditEmployeeComponent ,canActivate:[authGuard]},
            { path: 'create-customer-account', component: CreateCustomerAccountComponent,canActivate:[authGuard] },
            { path: 'view-customers', component: ViewCustomersComponent ,canActivate:[authGuard]},
            { path: 'edit-customer/:id', component: EditCustomerComponent,canActivate:[authGuard] },

            { path: 'employee-dashboard', component: EmployeeDashboardComponent ,canActivate:[authGuard]},
            { path: 'deposit', component: DepositMoneyComponent ,canActivate:[authGuard]},
            { path: 'withdraw', component: WithdrawMoneyComponent ,canActivate:[authGuard]},
            { path: 'view-all-customers', component: ViewAllCustomersComponent,canActivate:[authGuard] },
            
            { path: 'customer-dashboard', component: CustomerDashboardComponent ,canActivate:[authGuard]},
            { path: 'view-balance', component: ViewBalanceComponent ,canActivate:[authGuard]},
            { path: 'transfer', component: TransferMoneyComponent ,canActivate:[authGuard]},
            { path: 'account-statement', component: AccountStatementComponent ,canActivate:[authGuard]},
            { path: 'update-details', component: UpdateDetailsComponent ,canActivate:[authGuard]}
        ]
    }
];
