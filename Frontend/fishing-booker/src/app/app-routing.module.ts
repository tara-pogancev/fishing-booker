import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { ThankYouRegistrationComponent } from './pages/register/thank-you-registration/thank-you-registration.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { BoatOwnerDashboardComponent } from './components/boats/boat-owner-dashboard/boat-owner-dashboard.component';
import { ClientDashboardComponent } from './components/client/client-dashboard/client-dashboard.component';
import { FishingInstructorDashboardComponent } from './components/fishing/fishing-instructor-dashboard/fishing-instructor-dashboard.component';
import { CottageOwnerDashboardComponent } from './components/cottages/cottage-owner-dashboard/cottage-owner-dashboard.component';
import { BrowseBoatsComponent } from './pages/browse-boats/browse-boats.component';
import { BrowseCottagesComponent } from './pages/browse-cottages/browse-cottages.component';
import { BrowseFishingComponent } from './pages/browse-fishing/browse-fishing.component';
import { BrowseSpecialOffersComponent } from './pages/browse-special-offers/browse-special-offers.component';
import { ReviewsPageComponent } from './pages/reviews-page/reviews-page.component';
import { BoatPageComponent } from './components/boats/boat-page/boat-page.component';
import { CottagePageComponent } from './components/cottages/cottage-page/cottage-page.component';
import { FishingPageComponent } from './components/fishing/fishing-page/fishing-page.component';
import { NotAllowedComponent } from './pages/not-allowed/not-allowed.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'thank-you-registration', component: ThankYouRegistrationComponent },
  { path: 'boat-owner-db', component: BoatOwnerDashboardComponent },
  { path: 'admin-db', component: AdminDashboardComponent },
  { path: 'client-db', component: ClientDashboardComponent },
  { path: 'instructor-db', component: FishingInstructorDashboardComponent },
  { path: 'cottage-owner-db', component: CottageOwnerDashboardComponent },
  { path: 'boat/:id', component: BoatPageComponent },
  { path: 'cottage/:id', component: CottagePageComponent },
  { path: 'adventure/:id', component: FishingPageComponent },
  { path: '', component: HomeComponent },
  { path: '401', component: NotAllowedComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
