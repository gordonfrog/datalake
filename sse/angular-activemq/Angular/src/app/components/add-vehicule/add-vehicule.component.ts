import { Component, OnInit, ViewChild } from '@angular/core';
import { VehiculeService } from '../../services/vehicule.service';
import { Car } from '../../models/car';
import { Van } from '../../models/van';
import { ModalDirective } from 'angular-bootstrap-md/modals/modal.directive';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-vehicule',
  templateUrl: './add-vehicule.component.html',
  styleUrls: ['./add-vehicule.component.scss']
})
export class AddVehiculeComponent implements OnInit {

  @ViewChild('vanBadInput') vanBadInput: ModalDirective;
  @ViewChild('addVanForm') addVanForm: NgForm;
  @ViewChild('carBadInput') carBadInput: ModalDirective;
  @ViewChild('addCarForm') addCarForm: NgForm;
  errorMessage: String = 'There was an error filling up the form. Please try again.';
  car = new Car();
  van = new Van();
  choice: Boolean = false;

  constructor(private vehiculeService: VehiculeService) { }

  ngOnInit() {
  }

  onSubmitVan() {
    this.vehiculeService.addVan(this.van).subscribe(res => {
      window.location.href = '/vehicules';
    });
  }

  onSubmitCar() {
    this.vehiculeService.addCar(this.car).subscribe(res => {
      window.location.href = '/vehicules';
    });
  }

  toggleCar() {
    this.choice = false;
  }

  toggleVan() {
    this.choice = true;
  }

}
