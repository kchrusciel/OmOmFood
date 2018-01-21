import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SearchComponent } from './components/home/search/search.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

import { routing } from "./routing/app.routing";
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { HowItWorksComponent } from './components/home/how-it-works/how-it-works.component';
import { AddOfferComponent } from './components/home/add-offer/add-offer.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    SearchComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    FooterComponent,
    HowItWorksComponent,
    AddOfferComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
