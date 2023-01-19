import { Component, OnInit, ViewChild } from '@angular/core';
import { RentService } from '../../services/rent.service';
import { VehiculeService } from '../../services/vehicule.service';
import { Person } from '../../models/person';
import { Vehicule } from '../../models/vehicule';
import { Rent } from '../../models/rent';
import { NgForm } from '@angular/forms';
import { ModalDirective } from 'angular-bootstrap-md/modals/modal.directive';




@Component({
  selector: 'app-add-rent',
  templateUrl: './add-rent.component.html',
  styleUrls: ['./add-rent.component.scss']
})
export class AddRentComponent implements OnInit {

  @ViewChild('addRentForm') addRentForm: NgForm;
  @ViewChild('badInput') badInput: ModalDirective;
  errorMessage: String = 'There was an error when filling up the form. Please try again.';
  rent = new Rent();
  receivedPerson;
  vehiculeList = new Array<Vehicule>();

  constructor(private rentService: RentService, private vehiculeService: VehiculeService) { }

  ngOnInit() {
    this.receivedPerson = JSON.parse(localStorage.getItem('user'));
    this.vehiculeService.getCars().subscribe(cars => {
      for (let i = 0; i < cars.length; i++) {
        this.vehiculeList.push(cars[i]);
      }
    });
    this.vehiculeService.getVans().subscribe(vans => {
      for (let i = 0; i < vans.length; i++) {
        this.vehiculeList.push(vans[i]);
      }
    });
  }

  onSubmit() {
    this.rent.person = this.receivedPerson;
    this.rent.numberOfDays = this.rentService.computeDays(this.rent);
    this.rent.finalPrice = this.rentService.computePrice(this.rent);
    if (this.rent.beginRent > this.rent.endRent) {
      this.errorMessage = 'You must specify valid begin and end date. Please try again.';
      this.badInput.show();

    } else {
      this.rentService.addRent(this.rent.person.id, this.rent).subscribe(res => {
        window.location.href = '/rents';
      });
    }
  }

}
