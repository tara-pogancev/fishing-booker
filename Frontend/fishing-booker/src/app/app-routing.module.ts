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
import { EditAdventureComponent } from './components/fishing/fishing-instructor-dashboard/edit-adventure/edit-adventure.component';
import { ChangeAdventureComponent } from './components/fishing/fishing-instructor-dashboard/change-adventure/change-adventure.component';
import { CottageOwnerCottagePreviewComponent } from './components/cottages/cottage-owner-dashboard/cottage-owner-cottage-preview/cottage-owner-cottage-preview.component';
import { CottageOwnerEditCottageComponent } from './components/cottages/cottage-owner-dashboard/cottage-owner-edit-cottage/cottage-owner-edit-cottage.component';
import { NewBoatReservationComponent } from './components/client/new-boat-reservation/new-boat-reservation.component';
import { NewCottageReservationComponent } from './components/client/new-cottage-reservation/new-cottage-reservation.component';
import { NewAdventureReservationComponent } from './components/client/new-adventure-reservation/new-adventure-reservation.component';
import { AdventureReservationReport } from './model/adventure-reservation-report';
import { AdventureReservationReportComponent } from './components/fishing/fishing-instructor-dashboard/adventure-reservation-report/adventure-reservation-report.component';
import { ErrorComponent } from './pages/error/error.component';
import { CottageReservationReportComponent } from './components/cottages/cottage-owner-dashboard/cottage-reservation-report/cottage-reservation-report.component';
import { BoatOwnerBoatPreviewComponent } from './components/boats/boat-owner-dashboard/boat-owner-boat-preview/boat-owner-boat-preview.component';
import { BoatOwnerEditBoatComponent } from './components/boats/boat-owner-dashboard/boat-owner-edit-boat/boat-owner-edit-boat.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'boats', component: BrowseBoatsComponent },
  {
    path: 'resertvation/new/boat/:id/:startDate/:endDate/:people',
    component: NewBoatReservationComponent,
  },
  {
    path: 'resertvation/new/adventure/:id/:startDate/:endDate/:people',
    component: NewAdventureReservationComponent,
  },
  {
    path: 'resertvation/new/cottage/:id/:startDate/:endDate/:people',
    component: NewCottageReservationComponent,
  },
  { path: 'cottages', component: BrowseCottagesComponent },
  { path: 'fishing', component: BrowseFishingComponent },
  { path: 'special-offers', component: BrowseSpecialOffersComponent },
  { path: 'reviews', component: ReviewsPageComponent },
  { path: 'thank-you-registration', component: ThankYouRegistrationComponent },
  { path: 'boat-owner-db', component: BoatOwnerDashboardComponent },
  { path: 'admin-db', component: AdminDashboardComponent },
  { path: 'client-db', component: ClientDashboardComponent },
  { path: 'client-db/:page', component: ClientDashboardComponent },
  { path: 'instructor-db', component: FishingInstructorDashboardComponent },
  { path: 'cottage-owner-db', component: CottageOwnerDashboardComponent },
  { path: 'boat/:id', component: BoatPageComponent },
  { path: 'cottage/:id', component: CottagePageComponent },
  { path: 'adventure/:id', component: FishingPageComponent },
  { path: 'preview-adventure/:id', component: EditAdventureComponent },
  { path: 'edit-adventure/:id', component: ChangeAdventureComponent },
  { path: 'preview-cottage/:id', component: CottageOwnerCottagePreviewComponent },
  { path: 'edit-cottage/:id', component: CottageOwnerEditCottageComponent },
  { path: 'preview-boat/:id', component: BoatOwnerBoatPreviewComponent },
  { path: 'edit-boat/:id', component: BoatOwnerEditBoatComponent },
  { path: '', component: HomeComponent },
  { path: '401', component: NotAllowedComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'adventure-reservation-report/:id',component: AdventureReservationReportComponent},
  { path: 'cottage-reservation-report/:id',component: CottageReservationReportComponent},
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
