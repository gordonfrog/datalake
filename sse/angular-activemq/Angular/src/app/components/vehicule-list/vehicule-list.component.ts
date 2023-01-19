import { Component, OnInit, ViewChild } from '@angular/core';
import { VehiculeService } from '../../services/vehicule.service';
import { ModalDirective } from '../../../../node_modules/angular-bootstrap-md';
import { NgForm } from '@angular/forms';
import { Car } from '../../models/car';
import { Van } from '../../models/van';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-vehicule-list',
  templateUrl: './vehicule-list.component.html',
  styleUrls: ['./vehicule-list.component.scss']
})
export class VehiculeListComponent implements OnInit {

  @ViewChild('updateCarModal') updateCarModal: ModalDirective;
  @ViewChild('updateVanModal') updateVanModal: ModalDirective;
  @ViewChild('deleteCarConf') deleteCarConf: ModalDirective;
  @ViewChild('deleteVanConf') deleteVanConf: ModalDirective;
  @ViewChild('carBadInput') carBadInput: ModalDirective;
  @ViewChild('vanBadInput') vanBadInput: ModalDirective;
  @ViewChild('updateCarForm') updateCarForm: NgForm;
  @ViewChild('updateVanForm') updateVanForm: NgForm;
  errorMessage: String = 'There was an error filling up the form. Please try again.';
  cars = new Array<Car>();
  car = new Car();
  vans = new Array<Van>();
  van = new Van();
  sortDirectionCar: String = 'asc';
  sortDirectionVan: String = 'asc';
  sortFieldCar: String = 'model';
  sortFieldVan: String = 'model';
  sortFieldsCar: Array<String> = [
    'model',
    'plateNumber',
    'seats',
    'price'
  ];
  sortFieldsVan: Array<String> = [
    'model',
    'plateNumber',
    'maxWeight',
    'price'
  ];

  constructor(private vehiculeService: VehiculeService) { }

  ngOnInit() {
    this.vehiculeService.getCars().subscribe(cars => {
      this.cars = cars;
    });
    this.vehiculeService.getVans().subscribe(vans => {
      this.vans = vans;
    });
  }

  onDeleteClickCar(vehiculeId) {
    this.deleteCarConf.show();
    this.car.vehiculeId = vehiculeId;
  }

  onDeleteClickVan(vehiculeId) {
    this.deleteVanConf.show();
    this.van.vehiculeId = vehiculeId;
  }

  onSubmitCar() {
    let cpt;
    this.car.model = this.updateCarForm.value.model;
    this.car.plateNumber = this.updateCarForm.value.p_number;
    this.car.seats = this.updateCarForm.value.seats;
    this.car.price = this.updateCarForm.value.price;
    this.vehiculeService.updateCar(this.car).subscribe(res => {
      for (let i = 0; i < this.cars.length; i++) {
        if (this.cars[i].vehiculeId === this.car.vehiculeId) {
          this.cars.splice(i, 1);
          cpt = i;
        }
      }
      this.cars.splice(cpt, 0, this.car);
      this.updateCarModal.hide();
    });
  }

  onSubmitVan() {
    let cpt;
    this.van.model = this.updateVanForm.value.model;
    this.van.plateNumber = this.updateVanForm.value.p_number;
    this.van.maxWeight = this.updateVanForm.value.weight;
    this.van.price = this.updateVanForm.value.price;
    this.vehiculeService.updateVan(this.van).subscribe(res => {
      for (let i = 0; i < this.vans.length; i++) {
        if (this.vans[i].vehiculeId === this.van.vehiculeId) {
          this.vans.splice(i, 1);
          cpt = i;
        }
      }
      this.vans.splice(cpt, 0, this.van);
      this.updateVanModal.hide();
    });
  }

  onEditClickCar(car: Car) {
    this.car = car;
    this.updateCarModal.show();
  }

  onEditClickVan(van: Van) {
    this.van = van;
    this.updateVanModal.show();
  }

  onProceedCar() {
    this.vehiculeService.deleteCar(this.car.vehiculeId).subscribe(res => {
      for (let i = 0; i < this.cars.length; i++) {
        if (this.cars[i].vehiculeId === this.car.vehiculeId) {
          this.cars.splice(i, 1);
        }
      }
      this.deleteCarConf.hide();
    });
  }

  onProceedVan() {
    this.vehiculeService.deleteVan(this.van.vehiculeId).subscribe(res => {
      for (let i = 0; i < this.vans.length; i++) {
        if (this.vans[i].vehiculeId === this.van.vehiculeId) {
          this.vans.splice(i, 1);
        }
      }
      this.deleteVanConf.hide();
    });
  }

  onAscCar() {
    this.changeStyleCarAsc();
    this.sortDirectionCar = 'asc';
  }

  onDescCar() {
    this.changeStyleCarDesc();
    this.sortDirectionCar = 'desc';
  }

  onAscVan() {
    this.changeStyleVanAsc();
    this.sortDirectionVan = 'asc';
  }

  onDescVan() {
    this.changeStyleVanDesc();
    this.sortDirectionVan = 'desc';
  }

  changeStyleCarAsc() {
    const button = document.getElementById('ascCar');
    const button2 = document.getElementById('descCar');
    if (button.click) {
      button.className = 'btn btn-light-green btn-sm';
      button.blur();
      button2.className = 'btn btn-mdb btn-sm';
    }
  }

  changeStyleCarDesc() {
    const button = document.getElementById('ascCar');
    const button2 = document.getElementById('descCar');
    if (button2.click) {
      button2.className = 'btn btn-light-green btn-sm';
      button2.blur();
      button.className = 'btn btn-mdb btn-sm';
    }
  }

  changeStyleVanAsc() {
    const button = document.getElementById('ascVan');
    const button2 = document.getElementById('descVan');
    if (button.click) {
      button.className = 'btn btn-light-green btn-sm';
      button.blur();
      button2.className = 'btn btn-mdb btn-sm';
    }
  }

  changeStyleVanDesc() {
    const button = document.getElementById('ascVan');
    const button2 = document.getElementById('descVan');
    if (button2.click) {
      button2.className = 'btn btn-light-green btn-sm';
      button2.blur();
      button.className = 'btn btn-mdb btn-sm';
    }
  }
}
