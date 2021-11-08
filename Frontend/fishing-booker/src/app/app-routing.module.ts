import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/pages/login/login.component';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { HomeComponent } from './components/pages/home/home.component';
import { RegisterComponent } from './components/pages/register/register.component';
import { ThankYouRegistrationComponent } from './components/pages/register/thank-you-registration/thank-you-registration.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { BoatOwnerDashboardComponent } from './components/boats/boat-owner-dashboard/boat-owner-dashboard.component';
import { ClientDashboardComponent } from './components/client/client-dashboard/client-dashboard.component';
import { FishingInstructorDashboardComponent } from './components/fishing/fishing-instructor-dashboard/fishing-instructor-dashboard.component';
import { CottageOwnerDashboardComponent } from './components/cottages/cottage-owner-dashboard/cottage-owner-dashboard.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'thank-you-registration', component: ThankYouRegistrationComponent },
  { path: 'boat-owner-db', component: BoatOwnerDashboardComponent },
  { path: 'admin-db', component: AdminDashboardComponent },
  { path: 'client-db', component: ClientDashboardComponent },
  { path: 'instructor-db', component: FishingInstructorDashboardComponent },
  { path: 'cottage-owner-db', component: CottageOwnerDashboardComponent },
  { path: '', component: HomeComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
