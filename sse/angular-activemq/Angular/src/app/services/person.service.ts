import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Person } from '../models/person';
import 'rxjs/add/operator/map';

@Injectable()
export class PersonService {

  constructor(private http: Http) { }


  getPersons() {
    return this.http.get('http://localhost:8080/rest/persons/all').map(res => res.json());
  }

  addPerson(person: Person) {
    return this.http.post('http://localhost:8080/rest/persons/add', person).map(res => res.json());
  }

  updatePerson(person: Person) {
    return this.http.put('http://localhost:8080/rest/persons/' + person.id, person).map(res => res.json());
  }

  deletePerson(id) {
    return this.http.delete('http://localhost:8080/rest/persons/' + id).map(res => res.json());
  }
}
