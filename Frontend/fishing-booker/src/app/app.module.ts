import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CottageCardComponent } from './components/cottages/cottage-card/cottage-card.component';
import { HeroTitleComponent } from './components/hero-title/hero-title.component';
import { TruncatePipe } from './pipes/truncate-pipe';
import { GapComponent } from './components/gap/gap.component';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { LoginComponent } from './components/pages/login/login.component';
import { RegisterComponent } from './components/pages/register/register.component';
import { ThankYouRegistrationComponent } from './components/pages/register/thank-you-registration/thank-you-registration.component';
import { FormsModule } from '@angular/forms';
import { UserHeaderComponent } from './components/header/user-header/user-header.component';
import { NewUserHeaderComponent } from './components/header/new-user-header/new-user-header.component';
import { ClientDashboardComponent } from './components/client/client-dashboard/client-dashboard.component';
import { BoatOwnerDashboardComponent } from './components/boats/boat-owner-dashboard/boat-owner-dashboard.component';
import { CottageOwnerDashboardComponent } from './components/cottages/cottage-owner-dashboard/cottage-owner-dashboard.component';
import { FishingInstructorDashboardComponent } from './components/fishing/fishing-instructor-dashboard/fishing-instructor-dashboard.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { BoatCardComponent } from './components/boats/boat-card/boat-card.component';
import { AdventureCardComponent } from './components/fishing/adventure-card/adventure-card.component';

@NgModule({
  declarations: [
    TruncatePipe,
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    CottageCardComponent,
    HeroTitleComponent,
    GapComponent,
    NotFoundComponent,
    LoginComponent,
    RegisterComponent,
    ThankYouRegistrationComponent,
    UserHeaderComponent,
    NewUserHeaderComponent,
    ClientDashboardComponent,
    BoatOwnerDashboardComponent,
    CottageOwnerDashboardComponent,
    FishingInstructorDashboardComponent,
    AdminDashboardComponent,
    BoatCardComponent,
    AdventureCardComponent,    
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
