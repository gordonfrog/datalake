import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Car } from '../models/car';
import { Van } from '../models/van';
import 'rxjs/add/operator/map';

@Injectable()
export class VehiculeService {

  constructor(private http: Http) { }

  getCars() {
    return this.http.get('http://localhost:8080/rest/vehicules/cars/all').map(res => res.json());
  }

  getVans() {
    return this.http.get('http://localhost:8080/rest/vehicules/vans/all').map(res => res.json());
  }

  addCar(car: Car) {
    return this.http.post('http://localhost:8080/rest/vehicules/cars/add', car).map(res => res.json());
  }

  addVan(van: Van) {
    return this.http.post('http://localhost:8080/rest/vehicules/vans/add', van).map(res => res.json());
  }

  updateCar(car: Car) {
    return this.http.put('http://localhost:8080/rest/vehicules/cars/' + car.vehiculeId, car).map(res => res.json());
  }

  updateVan(van: Van) {
    return this.http.put('http://localhost:8080/rest/vehicules/vans/' + van.vehiculeId, van).map(res => res.json());
  }

  deleteCar(vehiculeId) {
    return this.http.delete('http://localhost:8080/rest/vehicules/cars/' + vehiculeId).map(res => res.json());
  }

  deleteVan(vehiculeId) {
    return this.http.delete('http://localhost:8080/rest/vehicules/vans/' + vehiculeId).map(res => res.json());
  }

}
