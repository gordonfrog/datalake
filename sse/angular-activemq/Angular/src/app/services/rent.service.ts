import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Rent } from '../models/rent';
import 'rxjs/add/operator/map';

@Injectable()
export class RentService {

  constructor(private http: Http) { }

  getRents(id) {
    return this.http.get('http://localhost:8080/rest/persons/' + id + '/rents/all').map(res => res.json());
  }

  addRent(id, rent: Rent) {
    return this.http.post('http://localhost:8080/rest/persons/' + id + '/rents/add', rent).map(res => res.json());
  }

  updateRent(id, rent: Rent) {
    return this.http.put('http://localhost:8080/rest/persons/' + id + '/rents/' + rent.rentId, rent).map(res => res.json());
  }

  deleteRent(id, rentId) {
    return this.http.delete('http://localhost:8080/rest/persons/' + id + '/rents/' + rentId).map(res => res.json());
  }

  sqlToJsDate(sqlDate) {
    const dateStr = sqlDate;
    const a = dateStr.split(' ');
    const d = a[0].split('-');
    const formattedDate = new Date(d[0], d[1] - 1, d[2]);
    return formattedDate;
  }

  computeDays(rent: Rent) {
    const day = 24 * 60 * 60 * 1000;
    const formattedBegin = this.sqlToJsDate(rent.beginRent);
    const formattedEnd = this.sqlToJsDate(rent.endRent);
    const diffTime = Math.abs(formattedEnd.getTime() - formattedBegin.getTime());
    const diffDays = Math.ceil(diffTime / day);
    return diffDays;
  }

  computePrice(rent) {
    return (rent.vehicule.price * rent.numberOfDays);
  }
}
