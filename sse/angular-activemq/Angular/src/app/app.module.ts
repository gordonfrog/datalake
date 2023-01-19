import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { PersonService } from './services/person.service';
import { VehiculeService } from './services/vehicule.service';
import { RentService } from './services/rent.service';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PersonsListComponent } from './components/persons-list/persons-list.component';
import { HomeComponent } from './components/home/home.component';
import { AddPersonComponent } from './components/add-person/add-person.component';
import { VehiculeListComponent } from './components/vehicule-list/vehicule-list.component';
import { AddVehiculeComponent } from './components/add-vehicule/add-vehicule.component';
import { RentListComponent } from './components/rent-list/rent-list.component';
import { AddRentComponent } from './components/add-rent/add-rent.component';
import { SortByPipe } from './pipes/sort-by.pipe';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'persons', component: PersonsListComponent },
  { path: 'vehicules', component: VehiculeListComponent },
  { path: 'rents', component: RentListComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PersonsListComponent,
    HomeComponent,
    AddPersonComponent,
    VehiculeListComponent,
    AddVehiculeComponent,
    RentListComponent,
    AddRentComponent,
    SortByPipe
  ],
  imports: [
    BrowserModule,
    MDBBootstrapModule.forRoot(),
    HttpModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  providers: [PersonService, VehiculeService, RentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
