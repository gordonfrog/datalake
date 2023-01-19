import { Component, OnInit, ViewChild } from '@angular/core';
import { PersonService } from '../../services/person.service';
import { ModalDirective } from '../../../../node_modules/angular-bootstrap-md';
import { NgForm } from '@angular/forms';
import { Person } from '../../models/person';


@Component({
  selector: 'app-persons-list',
  templateUrl: './persons-list.component.html',
  styleUrls: ['./persons-list.component.scss']
})
export class PersonsListComponent implements OnInit {

  @ViewChild('updateModal') updateModal: ModalDirective;
  @ViewChild('deleteConf') deleteConf: ModalDirective;
  @ViewChild('updateForm') updateForm: NgForm;
  persons = new Array<Person>();
  person = new Person();
  sortDirection: String = 'asc';
  sortField: String = 'name';
  sortFields: Array<String> = [
    'name',
    'date'
  ];

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.personService.getPersons().subscribe(persons => {
      this.persons = persons;
    });
  }

  onDeleteClick(id) {
    this.deleteConf.show();
    this.person.id = id;
  }

  onSubmit() {
    let cpt;
    this.person.name = this.updateForm.value.name;
    this.person.date = this.updateForm.value.date;
    this.personService.updatePerson(this.person).subscribe(res => {
      for (let i = 0; i < this.persons.length; i++) {
        if (this.persons[i].id === this.person.id) {
          this.persons.splice(i, 1);
          cpt = i;
        }
      }
      this.persons.splice(cpt, 0, this.person);
      this.updateModal.hide();
    });
  }

  onEditClick(person: Person) {
    this.person = person;
    this.updateModal.show();
  }

  onProceed() {
    this.personService.deletePerson(this.person.id).subscribe(res => {
      for (let i = 0; i < this.persons.length; i++) {
        if (this.persons[i].id === this.person.id) {
          this.persons.splice(i, 1);
        }
      }
      this.deleteConf.hide();
    });
  }

  onDetails(person: Person) {
    localStorage.setItem('user', JSON.stringify(person));
  }

  onAsc() {
    this.changeStyleAsc();
    this.sortDirection = 'asc';
  }

  onDesc() {
    this.changeStyleDesc();
    this.sortDirection = 'desc';
  }

  changeStyleAsc() {
    const button = document.getElementById('asc');
    const button2 = document.getElementById('desc');
    if (button.click) {
      button.className = 'btn btn-light-green btn-sm';
      button.blur();
      button2.className = 'btn btn-mdb btn-sm';
    }
  }

  changeStyleDesc() {
    const button = document.getElementById('asc');
    const button2 = document.getElementById('desc');
    if (button2.click) {
      button2.className = 'btn btn-light-green btn-sm';
      button2.blur();
      button.className = 'btn btn-mdb btn-sm';
    }
  }
}
