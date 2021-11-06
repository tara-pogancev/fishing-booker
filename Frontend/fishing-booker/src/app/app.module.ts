import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CottageCardComponent } from './components/cottage-card/cottage-card.component';
import { HeroTitleComponent } from './components/hero-title/hero-title.component';
import { TruncatePipe } from './pipes/truncate-pipe';
import { GapComponent } from './components/gap/gap.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ThankYouRegistrationComponent } from './register/thank-you-registration/thank-you-registration.component';

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
    ThankYouRegistrationComponent
  ],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
